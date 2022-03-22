package com.example.demo.bindings;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class User {
	private Integer userId;
	private String userFname;
	private String userLname;
	private String userEmaill;
	private Integer usePhNo;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate userDOB;
	private String userGender;
	private Integer userCountry;
	private Integer userState;
	private Integer userCity;
	private String userPwd;
	private LocalDate createdDate;
	private LocalDate updateDate;
	private String setUserAccStatus; 
		
	

}
