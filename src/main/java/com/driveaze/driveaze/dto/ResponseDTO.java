package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.controller.ManHourPricingController;
import com.driveaze.driveaze.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String expirationTime;
    private String name;
    private String role;
    private String email;
    private String contactNumber;
    private OurUsers ourUsers;
    private List<OurUsers> ourUsersList;
    private CustomerVehicle customerVehicle;
    private List<CustomerVehicle> customerVehicleList;
    private JobRegistry jobRegistry;
    private List<JobRegistry> jobRegistryList;
    private Supplier supplier;
    private List<Supplier> supplierList;
    private VehicleModel vehicleModel;
    private List<VehicleModel> vehicleModelList;
    private VehicleBrand vehicleBrand;
    private List<VehicleBrand> vehicleBrandList;
    private TechnicianCategory technicianCategory;
    private List<TechnicianCategory> technicianCategoryList;
    private Inventory inventoryItem;
    private List<Inventory> inventoryItemList;
    private Booking booking;
    private List<Booking> bookingList;  // Initialize as empty list
    private SupplierInvoice supplierInvoice;
    private List<SupplierInvoice> supplierInvoiceList;
    private SupplierInvoiceEntry supplierInvoiceEntry;
    private List<SupplierInvoiceEntry> supplierInvoiceEntryList;
    private Bill bill;
    private List<Bill> billList;
    private ServiceTypes serviceTypes;
    private List<ServiceTypes> serviceTypesList;
    private ManHourPricing manHourPricing;
    private List<ManHourPricing> manHourPricingList;
    private Object details;
    private JobEntry jobEntry;
    private List<JobEntry> jobEntryList;
    private BillEntry billEntry;
    private List<BillEntry> billEntryList;

}
