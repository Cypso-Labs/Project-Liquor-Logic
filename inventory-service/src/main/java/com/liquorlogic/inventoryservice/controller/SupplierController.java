package com.liquorlogic.inventoryservice.controller;

import com.liquorlogic.inventoryservice.dto.SupplierDTO;
import com.liquorlogic.inventoryservice.entity.Enum.Status;
import com.liquorlogic.inventoryservice.entity.Supplier;
import com.liquorlogic.inventoryservice.enums.SupplierStatus;
import com.liquorlogic.inventoryservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {
    @Autowired
    private final SupplierService supplierService;

    private static final org.apache.logging.log4j.Logger loggerLog4J = LogManager.getLogger(SupplierController.class);

    //    REGISTER/UPDATE
    @PostMapping("/save")
    public ResponseEntity<Supplier> saveSupplier(@RequestBody Map<String, String> credentials) {
        loggerLog4J.info("Start register");
        try {
            String[] requiredFields = {
                    "item_id", "supplier_name", "email", "contact", "qty_received_items",
                    "buying_price", "payment", "payment_method", "qty_returned_items", "total_qty"
            };

            validateMap(credentials, requiredFields);

            Supplier supplier = new Supplier();
            UUID supplierId = credentials.get("id") != null ? UUID.fromString(credentials.get("id")) : null;

            if (supplierId != null) {
                Optional<Supplier> byID = supplierService.findBySupplierId(supplierId);
                if (byID.isPresent()) {
                    supplier.setId(supplierId);
                }
            }

            supplier.setItemId(UUID.fromString(credentials.get("item_id")).toString());
            supplier.setEmail(credentials.get("email"));
            supplier.setSupplierName(credentials.get("supplier_name"));
            supplier.setContact(credentials.get("contact"));
            supplier.setQtyReceivedItems(Integer.parseInt(credentials.get("qty_received_items")));
            supplier.setBuyingPrice(Double.parseDouble(credentials.get("buying_price")));
            supplier.setPayment(Double.parseDouble(credentials.get("payment")));
            supplier.setPaymentMethod(credentials.get("payment_method"));
            supplier.setQtyReturnedItems(Integer.parseInt(credentials.get("qty_returned_items")));
            supplier.setTotalQty(Integer.parseInt(credentials.get("total_qty")));
            supplier.setStatus(SupplierStatus.paid);

            Date currentDate = new Date();
            supplier.setUpdate(currentDate);
            if (supplierId == null) {
                supplier.setCreate(currentDate);
            }

            return ResponseEntity.ok(supplierService.saveSupplier(supplier));
        } catch (Exception e) {
            handleException(e);
            loggerLog4J.error("Error occurred while saving supplier", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            loggerLog4J.info("End saveSupplier");
        }
    }

    private void validateMap(Map<String, String> assetCategoryMap,String[] requiredFields) {
        for (String field : requiredFields) {
            if (assetCategoryMap.get(field) == null || assetCategoryMap.get(field).isEmpty()) {
                throw new IllegalArgumentException("Not found " + field);
            }
        }
    }


    @GetMapping("/getAllSuppliers")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        loggerLog4J.info("Start getAllSuppliers");
        try {
            List<Supplier> allSuppliers = supplierService.getAllSuppliers();
            List<SupplierDTO> responseSuppliers = convertToResponseSupplierList(allSuppliers);

            loggerLog4J.info("End getAllSuppliers");
            return ResponseEntity.ok(responseSuppliers);
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findSupplier")
    public ResponseEntity<List<Supplier>> getUser(@RequestParam(required = false) SupplierStatus status,
                                               @RequestParam(required = false) UUID supplierId,
                                               @RequestParam(required = false) String contact) {

        try {
            Optional<Supplier> supplierList;
            if (status != null) {
                supplierList = supplierService.findAllByStatus(status);
            } else if (supplierId != null) {
                supplierList = supplierService.findSupplierById(supplierId);
            } else if (contact != null) {
                supplierList = supplierService.findByContact(contact);
            } else {
                return ResponseEntity.badRequest().build();
            }

            if (!supplierList.isEmpty()) {
                loggerLog4J.info("End getUser");
                return ResponseEntity.ok(Collections.singletonList(supplierList.get()));
            } else {
                loggerLog4J.info("End getUser");
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<SupplierDTO> convertToResponseSupplierList(List<Supplier> allSuppliers) {

        if (allSuppliers != null && !allSuppliers.isEmpty()) {
            return allSuppliers.stream()
                    .map(this::convertToResponseSupplierData)
                    .toList();
        } else {
            return Collections.emptyList();
        }

    }

    private SupplierDTO convertToResponseSupplierData(Supplier supplier) {
        return new SupplierDTO(
                supplier.getId(),
                supplier.getItemId(),
                supplier.getSupplierName(),
                supplier.getContact(),
                supplier.getEmail(),
                supplier.getQtyReceivedItems(),
                supplier.getQtyReturnedItems(),
                supplier.getTotalQty(),
                supplier.getBuyingPrice(),
                supplier.getPayment(),
                supplier.getPaymentMethod(),
                supplier.getStatus(),
                supplier.getCreate(),
                supplier.getUpdate()
        );
    }

    private void handleException(Exception e) {
        loggerLog4J.error("Error ", e);
        e.printStackTrace();
    }
}
