package com.gleestorm.cargo.colis.model;


import com.gleestorm.cargo.colis.enums.EnumState;
import com.gleestorm.cargo.core.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "PACKAGE_GROUP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageGroup extends BaseEntity {
    private List<String> packageIDS;
    private Instant sendAt;
    private Instant arrivedAt;
    private String type;
    private String departureCountry;
    private String targetCountry;
    private String departureCity;
    private String targetCity;
    private Double weight;
    private Integer packageNumber;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private EnumState state;
    private String additionnalInfos;
    @OneToMany(mappedBy="group")
    private List<Package> packageList;
}
