package com.liquorlogic.inventoryservice.service;

import com.liquorlogic.inventoryservice.entity.Supplier;
import com.liquorlogic.inventoryservice.enums.SupplierStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    public Supplier saveSupplier(Supplier supplier);

    Optional<Supplier> findAllByStatus(SupplierStatus status);

    Optional<Supplier> findSupplierById(UUID supplierId);

    Optional<Supplier> findByContact(String contact);

    Optional<Supplier> findBySupplierId(UUID supplierId);
}
