package npscommunity.admin.Dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDto {
	@NotNull
	private final String username;
	@NotNull
	private final String password;
}
