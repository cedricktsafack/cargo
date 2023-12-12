package com.gleestorm.cargo.packages.dto;

import com.gleestorm.cargo.authentication.model.User;
import com.gleestorm.cargo.packages.enums.EnumPackageType;
import com.gleestorm.cargo.packages.enums.EnumStatePackage;
import com.gleestorm.cargo.packages.model.PackageGroup;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.Instant;

@Data
public class CreatePackageRequest {
    private String name;

   private String  clientId;

    private String senderPhone;


    private String receiverName;
    private String receiverPhone;

    private String description;
    private Double weight;
    private String depatureCountry;
    private String targetCountry;
    private String departureCity;
    private String targetCity;
    private EnumPackageType type;
    private EnumStatePackage status = EnumStatePackage.NEW;
    private String groupID;
    private Instant sendAt;
    private Instant arrivedCountryDate;
    private Instant arrivedOfficeDate;
    private String photoURL;
    private Double pricePerDay;
    private Double pricePerWeight;


    @ManyToOne
    @JoinColumn(name = "group_id")
    private PackageGroup group;


    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by_id")
    private User updatedBy;
    private String additionnalInfos;
    private Instant removedAt;

}
