package com.liquorlogic.inventoryservice.entity;

import com.liquorlogic.inventoryservice.enums.SupplierStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ITEM_ID")
    private String itemId;

    @Column(name = "SUPPLIER_NAME")
    private String supplierName;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "QTY_RECEIVED_ITEMS")
    private int qtyReceivedItems;

    @Column(name = "QTY_RETURNED_ITEMS")
    private int qtyReturnedItems;

    @Column(name = "TOTAL_QTY")
    private int totalQty;

    @Column(name = "BUYING_PRICE")
    private double buyingPrice;

    @Column(name = "PAYMENT")
    private double payment;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private SupplierStatus status;

    @Column(name = "CREATE_DATE")
    private Date create;

    @Column(name = "UPDATE_DATE")
    private Date update;
}
