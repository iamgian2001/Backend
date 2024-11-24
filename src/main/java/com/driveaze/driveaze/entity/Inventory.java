package com.driveaze.driveaze.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    @Column(name = "name", length =500, nullable = false, unique = true)
    private String name;
    @Column(name = "count", length = 10)
    private int count;
    @Column(name = "initial_count", length = 10)
    private int initialCount;
    @Column(name = "selling_price", nullable = false)
    private BigDecimal sellingPrice;
    public Inventory() {
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Inventory(int itemId, String name, int count, int initialCount, BigDecimal sellingPrice) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.initialCount = initialCount;
        this.sellingPrice = sellingPrice;
    }

    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getInitialCount() {
        return initialCount;
    }
    public void setInitialCount(int intialCount) {
        this.initialCount = intialCount;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", initialCount=" + initialCount +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}