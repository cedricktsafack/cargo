package com.gleestorm.cargo.packages.model;


import com.gleestorm.cargo.authentication.model.User;
import com.gleestorm.cargo.packages.enums.EnumPackageType;
import com.gleestorm.cargo.packages.enums.EnumStatePackage;
import com.gleestorm.cargo.core.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "PACKAGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Package extends BaseEntity {
    private String name;
    private String senderPhone;
    private String description;
    private Double weight;
    private String depatureCountry;
    private String targetCountry;
    private String departureCity;
    private String targetCity;
    @Enumerated(EnumType.STRING)
    private EnumPackageType type;
    @Enumerated(EnumType.STRING)
    private EnumStatePackage status;
    private String groupID;
    private Instant sendAt;
    private Instant arrivedCountryDate;
    private Instant arrivedOfficeDate;
    private String photoURL;
    private Double pricePerDay;
    private Double pricePerWeight;
    private String receiverName;
    private String receiverPhone;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PackageGroup group;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_updatePackages",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id")
    )
    private List<User> userHoUpdatePackage = new ArrayList<>();


    private String additionnalInfos;
    private Instant removedAt;
}
