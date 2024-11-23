package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.BillEntryDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

public interface BillEntryService {
    ResponseDTO addNewBillEntry(BillEntryDTO billEntryDTO);

    ResponseDTO getAllBillEntries();

    ResponseDTO updateBillEntry(Integer billEntryId, BillEntryDTO billEntryDTO);

    ResponseDTO deleteBillEntry(Integer billEntryId);

    ResponseDTO getBillEntryById(Integer billEntryId);
}
