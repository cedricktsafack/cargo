package com.gleestorm.cargo.core.utils.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponsePaging{
    private Integer totalPages;
    private Integer totalElements;
    private Integer page;
    private Integer pageSize;
}