package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.BillDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Bill;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {
    ResponseDTO addNewBill(BillDTO billDTO);

    ResponseDTO getAllBills();

    ResponseDTO updateBill(Integer billId, BillDTO billDTO);

    ResponseDTO deleteBill(Integer billId);

    ResponseDTO getBillById(Integer billId);

    ResponseDTO getAllBillsWithStatus(Integer billStatus);

    Page<Bill> findBillsWithPaginationAndSortingAndStatus(List<Integer> statuses, int offset);

    ResponseDTO updateBillStatus(Integer billId, int status);
}
