package com.sistemapacto.server.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginationDTO<T> {
    private Long totalElements;
    private Integer pageQuantity;
    private Integer page;
    private Integer size;
    private List<T> elements;
}
