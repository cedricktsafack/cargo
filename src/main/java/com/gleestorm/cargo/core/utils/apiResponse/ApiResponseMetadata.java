package com.gleestorm.cargo.core.utils.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMetadata {

    private ApiResponseError error;
    private ApiResponsePaging paging;
    private String traceId;
    private Instant processingStart;
    private Instant processingEnd;
}