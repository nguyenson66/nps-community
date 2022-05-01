package npscommunity.admin.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterDto {

	@NotBlank
	@Size(max = 50, min = 6)
	private final String username;
	
	@NotBlank
	@Size(max = 100, min = 8)
	private final String password;
	
	@NotBlank
	@Size(max = 50, min = 6)
	private final String name;
}
