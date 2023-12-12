package com.gleestorm.cargo.packages.model;


import com.gleestorm.cargo.authentication.model.User;
import com.gleestorm.cargo.packages.enums.EnumPackageGroupType;
import com.gleestorm.cargo.core.base.entity.BaseEntity;
import com.gleestorm.cargo.packages.enums.EnumStatePackage;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "PACKAGE_GROUP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageGroup extends BaseEntity {
    private Instant sendAt;
    private Instant arrivedAt;
    @Enumerated(EnumType.STRING)
    private EnumPackageGroupType type;
    private String departureCountry;
    private String targetCountry;
    private String departureCity;
    private String targetCity;
    private Double weight;
    private Integer packageNumber;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private EnumStatePackage state;
    private String additionnalInfos;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Package> packageList;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_updatePackageGroups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "packageGroup_id")
    )
    private List<User> userHoUpdatePackageGroup = new ArrayList<>();
}
