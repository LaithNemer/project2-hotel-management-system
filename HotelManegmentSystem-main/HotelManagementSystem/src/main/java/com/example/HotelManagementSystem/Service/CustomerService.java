package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Configuration.JwtService;
import com.example.HotelManagementSystem.Service.Interface.CustomerInterface;
import com.example.HotelManagementSystem.auth.AuthenticationResponse;
import com.example.HotelManagementSystem.auth.AuthenticationService;
import com.example.HotelManagementSystem.dto.ChangePasswordDTO;
import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.entity.Customer;
import com.example.HotelManagementSystem.entity.User;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.CustomerRepository;
import com.example.HotelManagementSystem.repository.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerService implements CustomerInterface {


    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    CustomerRepository customerRepository;
      private UserRepositry userRepositry;




    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepositry userRepositry, JwtService jwtService, AuthenticationService authenticationService) {
        this.customerRepository=customerRepository;

        this.userRepositry= userRepositry;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Override
    public CustomerDTO insertCustomer(CustomerDTO customerDTO) {


        if (customerDTO.getUser().getUsername() == null || customerDTO.getUser().getUsername().isEmpty()) {
            throw new BadRequestException("Customer", "username");
        }
        if (customerDTO.getUser().getEmail() == null || customerDTO.getUser().getEmail().isEmpty()) {
            throw new BadRequestException("Customer", "email");
        }
       Optional<Customer> EmailIsExit=customerRepository.findByUserEmail(customerDTO.getUser().getEmail());
        if(EmailIsExit.isPresent()){
            System.out.println("Email Already Exist");
            throw new BadRequestException("Customer", "email"+" email is already exist");
        }

        if(customerDTO.getUser().getRole().name().toLowerCase().equals("admin")){
            throw new BadRequestException("Customer", "role");
        }

        User user=new User();
        user.setAge(customerDTO.getUser().getAge());

        user.setUsername(customerDTO.getUser().getUsername());
        user.setPassword(customerDTO.getUser().getPassword());
        user.setPhone(customerDTO.getUser().getPhone());
        user.setEmail(customerDTO.getUser().getEmail());
        user.setRole(customerDTO.getUser().getRole());

        Customer customer=new Customer();
        customer.setUser(user);
        user.setCustomer(customer);
    User uss=    userRepositry.save(user);
        customerDTO.setId(user.getId());

        var jwtToken = jwtService.generateToken(uss);
        var refreshToken = jwtService.generateRefreshToken(uss);
        authenticationService.saveUserToken(uss, jwtToken);
        AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();


        return customerDTO;
    }

    @Override
    public List<CustomerDTO>getCustomers(){
        List<User>users=userRepositry.findAll();
        List<CustomerDTO>customer=new ArrayList<>();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getRole().name().toLowerCase().equals("customer")){
                CustomerDTO customer1=new CustomerDTO();
                customer1.setUser(users.get(i));
                customer1.setId(users.get(i).getId());
                customer.add(customer1);

            }
        }

        return  customer;
    }

    @Override
    public CustomerDTO getCustomer(int id) {
        Customer customer=customerRepository.getById(id);

        Optional<User> user = userRepositry.findById(customer.getUser().getId());
        if (user.isPresent()) {
            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setId(id);
            customerDTO.setUser(user.get());

            return customerDTO;
        } else {
            throw new BadRequestException("Customer", "id " + id + " not found");
        }
    }

    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO custmr) {
        Customer customer=customerRepository.getById(id);
        User user=userRepositry.getById(customer.getUser().getId());


        user.setUsername(custmr.getUser().getUsername());
        user.setPhone(custmr.getUser().getPhone());
        user.setPassword(custmr.getUser().getPassword());
        user.setAge(custmr.getUser().getAge());
        user.setEmail(custmr.getUser().getEmail());
        userRepositry.save(user);
        custmr.setId(id);
        custmr.setUser(user);

        return  custmr;


//        Optional<Customer> customerOptional = customerRepository.findById(id);
//        if (customerOptional.isPresent()) {
//            Customer customer = customerOptional.get();
//            if (custmr.getUser().getUsername() != null) {
//                customer.getUser().setUsername(custmr.getUser().getUsername());
//            }
//            if (custmr.getUser().getPassword() != null) {
//                customer.getUser().setPassword(custmr.getUser().getPassword());
//            }
//
//
//            return customerRepository.save(customer);
//        } else {
//            throw new BadRequestException("Customer", "id " + id + " not found");
//        }
    }

    @Override
    public boolean deleteCustomer(int id) {
        Customer customer=customerRepository.getById(id);
//        System.out.println(customer.getUser().getRole());
        User user=userRepositry.getById(customer.getUser().getId());



        if(user.getId()>0){
            userRepositry.deleteById(customer.getUser().getId());
            return true;
        }
        return false;
    }

    @Override
    public String logIn(String email,String password){
        System.out.println(email);
        Optional<User>user=userRepositry.findByEmail(email);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                return  "Welcome  " ;
            }
        }


        return  "does not have an account using this email and password";

    }




    @Override
    public CustomerDTO changePassword(int id, ChangePasswordDTO changePasswordDTO){
        if(!changePasswordDTO.getNewpassword().equals(changePasswordDTO.getConfirmpassword())){
            throw new BadRequestException("Customer", "passwords do not match");
        }



        Customer customer=customerRepository.getById(id);
        User user=userRepositry.getById(customer.getUser().getId());

        if(user.getPassword().equals(changePasswordDTO.getOldpassword())){
            throw new BadRequestException("Customer","Old password is not correct");
        }

        user.setPassword(changePasswordDTO.getNewpassword());
        userRepositry.save(user);

        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setUser(user);
        customerDTO.setId(id);


        return customerDTO;

    }


}
