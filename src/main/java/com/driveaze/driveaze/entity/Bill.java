package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_id", length = 45)
    private int billId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private CustomerVehicle customerVehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private OurUsers ourUsers;

    @Column(name = "bill_date", nullable = false)
    private LocalDate billDate;

    @Column(name = "bill_time", nullable = false)
    private LocalTime billTime;

    //bill Status
    //0 - > Created(Not Finalized)
    //1 - > Finalized
    //2 - > Payment Completed
    @Column(name = "bill_status", nullable = false)
    private int billStatus;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillEntry> entries;
}
