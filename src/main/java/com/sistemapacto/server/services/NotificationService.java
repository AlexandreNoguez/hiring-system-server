package com.sistemapacto.server.services;

import com.sistemapacto.server.dto.job.JobDTO;
import com.sistemapacto.server.dto.user.UserDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationService {
    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender emailSender;

    public void sendApplicationNotification(UserDTO userDTO, UserDTO recruiter, JobDTO jobDTO) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(recruiter.getUserEmail());
            mimeMessageHelper.setSubject("subject");
            mimeMessageHelper.setText(geContentFromTemplate(userDTO, recruiter, jobDTO), true);
            emailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    public String geContentFromTemplate(UserDTO userDTO, UserDTO recruiter, JobDTO jobDTO) throws IOException, TemplateException, BusinessException {
        Map<String, Object> dados = new HashMap<>();
        Template template = null;

        dados.put("loginRecruiter", recruiter.getUserLogin());
        dados.put("loginCadidate", userDTO.getUserLogin());
        dados.put("email", from);
        dados.put("title", jobDTO.getTitle());

        template = fmConfiguration.getTemplate("application-notifyer.html");

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
    }
}
