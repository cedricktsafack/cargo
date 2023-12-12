package com.gleestorm.cargo.core.utils.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class ApiResponseErrorElement {
    private String key;
    private String cause;
}