package com.driveaze.driveaze.dto;

import jakarta.persistence.Column;

public class CustomerVehicleDTO {
    private int vehicleId;
    private String vehicleNo;
    private String brand;
    private String model;
    private int customerId;

    public CustomerVehicleDTO() {
    }

    public CustomerVehicleDTO(int vehicleId, String vehicleNo, String brand, String model, int customerId) {
        this.vehicleId = vehicleId;
        this.vehicleNo = vehicleNo;
        this.brand = brand;
        this.model = model;
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CustomerVehicleDTO{" +
                "vehicleId=" + vehicleId +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
