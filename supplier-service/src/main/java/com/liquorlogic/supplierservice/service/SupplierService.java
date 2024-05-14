package com.liquorlogic.supplierservice.service;

import com.liquorlogic.supplierservice.entity.Supplier;
import com.liquorlogic.supplierservice.enums.Status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierService {

    List<Supplier> getAllSuppliers();

    public Supplier saveSupplier(Supplier supplier);

    List<Supplier> findAllByStatus(Status status);

    Optional<Supplier> findSupplierById(UUID supplierId);

    List<Supplier> findByContact(String contact);

    Optional<Supplier> findBySupplierId(UUID supplierId);
}
