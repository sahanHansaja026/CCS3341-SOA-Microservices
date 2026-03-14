package com.globalbooks.orders_service.model;

import lombok.Data;

@Data
public class Order {
    private String id;
    private String bookTitle;
    private int quantity;
    private double total;
}