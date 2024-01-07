package com.example.storedemo2.objects;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "human_id")
    private Long humanId;
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "stock")
    private Long stock;
    @Column(name = "updated_at")
    private Timestamp updated_at;

    public Inventory(){}
    public Inventory(Long humanId,Long itemId){
        this.humanId = humanId;
        this.itemId = itemId;
        this.stock = 0L;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getHumanId() {
        return humanId;
    }
    public void setHumanId(Long humanId) {
        this.humanId = humanId;
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public Long getStock() {
        return stock;
    }
    public void setStock(Long stock) {
        this.stock = stock;
    }
    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
