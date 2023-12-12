package com.gleestorm.cargo.packages.repository;

import com.gleestorm.cargo.packages.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package,String> {

}
