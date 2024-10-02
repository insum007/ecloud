package com.ecloud.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
	
	@NotBlank(message = "UserName cannot be blank")
	private String username;
	
	@NotBlank(message = "Password cannot be blank")
	private String password;
	
	private boolean active = true;

}
