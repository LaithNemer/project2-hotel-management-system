package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.dto.ChangePasswordDTO;
import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.entity.Customer;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {


      CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer insertCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getUsername() == null || customerDTO.getUsername().isEmpty()) {
            throw new BadRequestException("Customer", "username");
        }
        if (customerDTO.getEmail() == null || customerDTO.getEmail().isEmpty()) {
            throw new BadRequestException("Customer", "email");
        }
       boolean EmailIsExit=customerRepository.existsByEmail(customerDTO.getEmail());
        if(EmailIsExit){
            System.out.println("Email Already Exist");
            throw new BadRequestException("Customer", "email"+" email is already exist");
        }
        Customer customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        return customerRepository.save(customer);
    }

    public List<Customer>getCustomers(){
        return  customerRepository.findAll();
    }

    public CustomerDTO getCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setUsername(customer.get().getUsername());
            customerDTO.setPassword(customer.get().getPassword());
            customerDTO.setFirstname(customer.get().getFirstname());
            customerDTO.setLastname(customer.get().getLastname());
            customerDTO.setEmail(customer.get().getEmail());
            customerDTO.setPhone(customer.get().getPhone());
            customerDTO.setId(id);



            return customerDTO;
        } else {
            throw new BadRequestException("Customer", "id " + id + " not found");
        }
    }

    public Customer updateCustomer(long id, CustomerDTO custmr) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (custmr.getUsername() != null) {
                customer.setUsername(custmr.getUsername());
            }
            if (custmr.getPassword() != null) {
                customer.setPassword(custmr.getPassword());
            }
            if (custmr.getFirstname() != null) {
                customer.setFirstname(custmr.getFirstname());
            }
            if (custmr.getLastname() != null) {
                customer.setLastname(custmr.getLastname());
            }
            if (custmr.getEmail() != null) {
                customer.setEmail(custmr.getEmail());
            }
            if (custmr.getPhone() != null) {
                customer.setPhone(custmr.getPhone());
            }
            return customerRepository.save(customer);
        } else {
            throw new BadRequestException("Customer", "id " + id + " not found");
        }
    }

    public boolean deleteCustomer(long id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String logIn(String email,String password){
        List<Customer>array=customerRepository.findByEmail(email);
        if(array.get(0).getPassword().equals(password)){
            return "Welcome "+array.get(0).getUsername() ;
        }
        return  "user does not exit";

    }

    public CustomerDTO changePassword(long id, ChangePasswordDTO changePasswordDTO){
        if(!changePasswordDTO.getNewpassword().equals(changePasswordDTO.getConfirmpassword())){
            throw new BadRequestException("Customer", "passwords do not match");
        }

        Customer customer=customerRepository.findById(id).get();
        System.out.println(customer.toString());
        customer.setPassword(changePasswordDTO.getNewpassword());
        customerRepository.save(customer);
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setFirstname(customer.getFirstname());
        customerDTO.setLastname(customer.getLastname());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setId(customer.getId());
        return customerDTO;

    }


}
