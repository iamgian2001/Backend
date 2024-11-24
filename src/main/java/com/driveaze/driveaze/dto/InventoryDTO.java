package com.driveaze.driveaze.dto;

import java.math.BigDecimal;

public class InventoryDTO {
    private int itemId;
    private String name;
    private int count;
    private int initialCount;
    private BigDecimal sellingPrice;

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public InventoryDTO() {
    }

    public InventoryDTO(int itemId, String name, int count, int initialCount, BigDecimal sellingPrice) {
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
    public void setInitialCount(int initialCount) {
        this.initialCount = initialCount;
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", initialCount=" + initialCount +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}