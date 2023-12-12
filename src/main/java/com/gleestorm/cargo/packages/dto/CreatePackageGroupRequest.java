package com.gleestorm.cargo.packages.dto;

import com.gleestorm.cargo.packages.enums.EnumPackageGroupType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePackageGroupRequest {
    private Instant arrivedAt;
    private EnumPackageGroupType type;
    private String departureCountry;
    private String targetCountry;
    private String departureCity;
    private String targetCity;
    private Double weight;
    private Integer packageNumber;
    private Double amount;
    private String additionnalInfos;
    private List<String> packageList;
}
