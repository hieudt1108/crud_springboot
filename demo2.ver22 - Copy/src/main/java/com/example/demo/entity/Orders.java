package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Orders")
@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = -297553281792804396L;

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "amount")
    private double amount;
    @Column(name = "customer_address")
    private String customer_address;
    @Column(name = "customer_email")
    private String customer_email;
    @Column(name = "customer_name")
    private String customer_name;
    @Column(name = "customer_phone")
    private String customer_phone;
    @Column(name = "done")
    private boolean done;

    public Orders() {
    }

    public Orders(int id, double amount, String customer_address, String customer_email, String customer_name, String customer_phone, boolean done) {
        this.id = id;
        this.amount = amount;
        this.customer_address = customer_address;
        this.customer_email = customer_email;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.done = done;
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

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", amount=" + amount +
                ", customer_address='" + customer_address + '\'' +
                ", customer_email='" + customer_email + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", done=" + done +
                '}';
    }
}
