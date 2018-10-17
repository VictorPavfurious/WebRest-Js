package com.vpavlenko.restTest.model;

import javax.persistence.*;

@Table(name = "TableMessage", schema = "restMessage")
@Entity
public class TableMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "MESSAGE")
    private String text;
    @Column(name = "orderNumber")
    private int orderNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
