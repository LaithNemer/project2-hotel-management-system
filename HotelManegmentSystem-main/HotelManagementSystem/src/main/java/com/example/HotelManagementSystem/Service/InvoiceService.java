package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.InvoiceInterface;
import com.example.HotelManagementSystem.dto.InvoiceDto;
import com.example.HotelManagementSystem.entity.Invoice;
import com.example.HotelManagementSystem.entity.Reservation;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.InvoiceRepository;
import com.example.HotelManagementSystem.repository.ReservationRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService implements InvoiceInterface {

    private InvoiceRepository invoiceRepository;
    private ReservationRepositry reservationRepositry;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository,ReservationRepositry reservationRepositry){
        this.invoiceRepository=invoiceRepository;
        this.reservationRepositry=reservationRepositry;
    }

    @Override
    public InvoiceDto insertInvoiceForCustomer(int id) {
        Reservation reservation=reservationRepositry.getById(id);
        if(!reservation.getStatus().equals("approve")){
            throw  new BadRequestException("Error"," because you are not approve");
        }
        int roomsnumber=reservation.getRooms().size();
        Invoice invoice=new Invoice();
        double totelprice=0;
        for(int i=0;i<reservation.getRooms().size();i++){
            totelprice=totelprice+reservation.getRooms().get(i).getPrice();
        }

        invoice.setStatus("Unpaid");
        invoice.setTotalprice(totelprice);
        invoice.setNumberofroom(roomsnumber);
        invoice.setReservation(reservation);

        InvoiceDto invoiceDto=new InvoiceDto();
        invoiceDto.setReservationId(id);
        invoiceDto.setTotalprice(totelprice);
        invoiceDto.setNumberofroom(roomsnumber);
        invoiceDto.setStatus("Unpaid");

        System.out.println(roomsnumber+"\n"+ totelprice)  ;

        invoiceRepository.save(invoice);
        invoiceDto.setId(invoice.getId());

        return invoiceDto;
    }

    @Override
    public InvoiceDto getInvoiseForAdmine(int id) {
        List<Invoice> invoice=invoiceRepository.findAll();
        InvoiceDto invoiceDto=new InvoiceDto();

        for(int i=0;i<invoice.size();i++){
            if(invoice.get(i).getReservation().getId()==id){
                invoiceDto.setId(invoice.get(i).getId());
                invoiceDto.setTotalprice(invoice.get(i).getTotalprice());
                invoiceDto.setNumberofroom(invoice.get(i).getNumberofroom());
                invoiceDto.setStatus(invoice.get(i).getStatus());
                invoiceDto.setReservationId(id);
            }
        }


        return invoiceDto;
    }
}
