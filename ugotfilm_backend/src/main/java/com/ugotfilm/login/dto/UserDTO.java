package com.ugotfilm.login.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {


	private int usercode;
	private String username;
	private String password;
	private Date joinDate;
	private String authRole;
	
	
	
	private String nickname;
//	private String gender;
//	private int birth;
	
	
	
}
