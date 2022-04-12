package com.app.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	

	
	public JwtResponse(String token, String username, String email, Long id, List<String> roles) {
		super();
		this.token = token;
		this.username = username;
		this.email = email;
		this.id = id;
		this.roles = roles;
	}
	private String token;
	private String type="Bearer";
	private String username;
	private String email;
	private Long id;
	private List<String> roles;
	
	

}
