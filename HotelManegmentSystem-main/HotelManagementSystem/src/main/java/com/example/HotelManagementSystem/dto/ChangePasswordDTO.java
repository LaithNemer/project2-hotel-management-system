package com.example.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String oldpassword;
    private String newpassword;
    private String confirmpassword;

}
