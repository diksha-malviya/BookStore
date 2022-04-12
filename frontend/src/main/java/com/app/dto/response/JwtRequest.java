package com.app.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JwtRequest  implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2328448009411938898L;
	private String username;
	private String password;
}
