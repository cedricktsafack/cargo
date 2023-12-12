package com.gleestorm.cargo.core.utils.apiResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseError{
    private Integer httpStatusCode;
    private String message;
    private Map<String, String> errors;
}