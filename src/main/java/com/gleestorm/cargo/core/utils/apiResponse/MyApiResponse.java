package com.gleestorm.cargo.core.utils.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyApiResponse<T> {
    private T data;
    private ApiResponseMetadata metadata;
}
