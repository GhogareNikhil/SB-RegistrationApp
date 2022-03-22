package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class UserEntity {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "USER_FNAME")
	private String userFname;
	@Column(name = "USER_LNAME")
	private String userLname;
	@Column(name = "USER_Email", unique = true)
	private String userEmaill;
	@Column(name = "USER_PHONENO")
	private Integer usePhNo;
	@Column(name = "USER_DOB")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate userDOB;
	@Column(name = "USER_GENDER")
	private String userGender;
	@Column(name = "USER_COUNTRY")
	private Integer userCountry;
	@Column(name = "USER_STATE")
	private Integer userState;
	@Column(name = "USER_CITY")
	private Integer userCity;
	@Column(name = "USER_PDW")
	private String userPwd;
	@Column(name = "CREATED_DATE")
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name = "UPDATE_DATE")
	@CreationTimestamp
	private LocalDate updateDate;
	@Column(name="SET_USER_ACC_STATUS")
	private String setUserAccStatus; 
     
}
