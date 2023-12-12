package com.gleestorm.cargo.packages.repository;

import com.gleestorm.cargo.packages.model.PackageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageGroupRepository extends JpaRepository<PackageGroup, String> {
}
