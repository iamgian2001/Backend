package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.BillDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

public interface BillService {
    ResponseDTO addNewBill(BillDTO billDTO);

    ResponseDTO getAllBills();

    ResponseDTO updateBill(Integer billId, BillDTO billDTO);

    ResponseDTO deleteBill(Integer billId);

    ResponseDTO getBillById(Integer billId);
}
