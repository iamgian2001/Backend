package com.driveaze.driveaze.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_vehicle")
public class CustomerVehicle {

    @Id
    @Column(name = "vehicle_id", length =45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleId;

    @Column(name = "vehicle_no", length =100, nullable = false)
    private String vehicleNo;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "model", length = 100)
    private String model;

    @Column(name = "customer_id", length = 100)
    private int customerId;

    public CustomerVehicle() {
    }

    public CustomerVehicle(int vehicleId, String vehicleNo, String brand, String model, int customerId) {
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
        return "CustomerVehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
