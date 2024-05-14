package com.liquorlogic.supplierservice.repository;

import com.liquorlogic.supplierservice.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
