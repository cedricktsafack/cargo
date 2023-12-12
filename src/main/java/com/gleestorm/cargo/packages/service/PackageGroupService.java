package com.gleestorm.cargo.packages.service;


import com.gleestorm.cargo.authentication.model.User;
import com.gleestorm.cargo.authentication.repository.UserRepository;
import com.gleestorm.cargo.core.service.BaseService;
import com.gleestorm.cargo.exceptions.UserNotFoundException;
import com.gleestorm.cargo.packages.dto.CreatePackageGroupRequest;
import com.gleestorm.cargo.packages.enums.EnumStatePackage;
import com.gleestorm.cargo.packages.model.Package;
import com.gleestorm.cargo.packages.model.PackageGroup;
import com.gleestorm.cargo.packages.repository.PackageGroupRepository;
import com.gleestorm.cargo.packages.repository.PackageRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackageGroupService extends BaseService {

    final PackageGroupRepository packageGroupRepository;
    final PackageRepository packageRepository;

    public PackageGroupService(UserRepository userRepository, PackageGroupRepository packageGroupRepository, PackageRepository packageRepository) {
        super(userRepository);
        this.packageGroupRepository = packageGroupRepository;
        this.packageRepository = packageRepository;
    }


    public PackageGroup createPackageGroup(CreatePackageGroupRequest pckRequest) throws UserNotFoundException {

        User currentConnectedUser = getCurrentConnectedUser();

        val packageGroup = PackageGroup.builder()
                .packageNumber(pckRequest.getPackageNumber())
                .additionnalInfos(pckRequest.getAdditionnalInfos())
                .amount(pckRequest.getAmount())
                .arrivedAt(pckRequest.getArrivedAt())
                .departureCity(pckRequest.getDepartureCity())
                .departureCountry(pckRequest.getDepartureCountry())
                .targetCity(pckRequest.getTargetCity())
                .targetCountry(pckRequest.getTargetCountry())
                .type(pckRequest.getType())
                .weight(pckRequest.getWeight()).build();

        packageGroup.setCreatedBy(currentConnectedUser);
        packageGroup.getUserHoUpdatePackageGroup().add(currentConnectedUser);
        packageGroup.setSendAt(Instant.now());
        packageGroup.setState(EnumStatePackage.NEW);

        packageGroup.setPackageList(getPackageOfPackageGroup(pckRequest.getPackageList()));

        return packageGroupRepository.save(packageGroup);


    }

    public PackageGroup sendPackageGroup(String packageGroupIO, String userHo) throws UserNotFoundException {

        PackageGroup packageGroupResult = packageGroupRepository.getReferenceById(packageGroupIO);

        User currentConnectedUser = getCurrentConnectedUser();

        packageGroupResult.setState(EnumStatePackage.SEND);//State à envoyé
        packageGroupResult.getUserHoUpdatePackageGroup().add(currentConnectedUser);//connected user
        packageGroupResult.setSendAt(Instant.now()); //send date

        var packages = packageGroupResult.getPackageList();

        var updatedPackages = new ArrayList<Package>();

        for (Package pkg : packages) {
            //Update packages informations
            pkg.setStatus(EnumStatePackage.SEND); //send status
            pkg.getUserHoUpdatePackage().add(currentConnectedUser); //user connected
            pkg.setSendAt(Instant.now()); //send date
            updatedPackages.add(pkg);
        }

        packageGroupResult.setPackageList(updatedPackages); //replace by the updated package


        return packageGroupRepository.save(packageGroupResult);
    }

    private List<Package> getPackageOfPackageGroup(List<String> packageListId) {

        return packageRepository.findAllById(packageListId);
    }

}
