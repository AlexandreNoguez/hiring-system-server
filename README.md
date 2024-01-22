# Sistema de Recrutamento interno

### Este é um projeto desenvolvido para gerenciar vagas internas, possibilitando que os candidatos saibam das vagas abertas em sua empresa.

### O programa foi criado para o processo seletivo da empresa Sistema Pacto concorrendo a uma vaga de desenvolvedor fullstack Java/Angular.

## Tecnologias Utilizadas
* Java para o lado do servidor
* Angular para o lado do cliente
* Spring boot Framework
* Spring security
* Maven
* Swagger-UI
* JUnit
* Liquibase
* Postgresql

## Como Executar o Programa:
1. Clone o repositório [hiring-system-client](https://github.com/AlexandreNoguez/hiring-system-client).
2. Clone o repositório [hiring-system-server](https://github.com/AlexandreNoguez/hiring-system-server).
3. Configure as variáveis de ambiente na sua IDE.
4. Configure seu banco de dados (utilizei o elephantsql).
5. Certifique-se de ter o Java 17 instalado.
6. Execute o servidor.
7. Execute o cliente com o comando `npm run dev`.

## Ferramenta utilizada no desenvolvimento
* Intellijj IDEA

* Tela de configuração da palavra secreta (token), URL, login e senha do banco de dados, diretamente no arquivo de configuração application.properties ou através da IDE, conforme imagem abaixo:

![Print da configuração de login e senha do banco](https://github.com/AlexandreNoguez/only-assets/blob/main/processo-seletivo-pacto/doc-sistemapacto-server.png?raw=true)

## Funcionalidades:
* Cadastro e gerenciamento de usuários com permissões para administrador(a), recrutador(a) e candidato(a), podendo criar outros cargos se necessário.
* Cadastro e gerenciamento de vagas na empresa.

## Novas funcionalidades pra uma próxima versão
- Na hora de escolher habilidades, colocar um botão para excluir da lista.
- Pelo front, fazer com que o ADMIN consiga editar e deletar habilidades.
- Permitir que o recrutador consiga editar e deletar vagas.
- Criar novos auth guards para rotas de criar habilidades e cadastrar vagas.
- Criar readme em inglês.

## Notas:
* Certifique-se de seguir as instruções para que a aplicação rode normalmente.
* No primeiro cadastro haverá um erro pois foi colocado um usuário padrão admin para que possa ser adicionada novas habilidades pelo lado do cliente mas também é possível a criação das habilidades utilizando o swagger por padrão na url <a>http://localhost:8080/swagger-ui/index.html#/</a>
* Para que o e-mail de notificação seja enviado, certifique-se de ter configurado corretamente.

# Agradecimento
### Agradeço pela oportunidade de desenvolver este projeto. Estou entusiasmado para contribuir e espero avançar para as próximas etapas do processo seletivo. Obrigado por considerar minha participação!
