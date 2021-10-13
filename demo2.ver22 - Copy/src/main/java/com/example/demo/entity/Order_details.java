package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "Order_details")
@Data
public class Order_details {
    private static final long serialVersionUID = -297553281792804396L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "amount")
    private double amount;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "order_id")
    private int order_id;
    @Column(name = "product_id")
    private int product_id;

    public Order_details() {
    }

    public Order_details(int id, double amount, double price, int quantity, int order_id, int product_id) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.quantity = quantity;
        this.order_id = order_id;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Order_details{" +
                "id=" + id +
                ", amount=" + amount +
                ", price=" + price +
                ", quantity=" + quantity +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                '}';
    }
}
