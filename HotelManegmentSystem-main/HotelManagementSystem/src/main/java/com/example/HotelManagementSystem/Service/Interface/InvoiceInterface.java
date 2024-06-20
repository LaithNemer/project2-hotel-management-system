package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.InvoiceDto;

public interface InvoiceInterface {
    InvoiceDto insertInvoiceForCustomer(int id);

    InvoiceDto getInvoiseForAdmine(int id);
}
