package com.usermanagement.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO for incoming user requests")
public class UserRequestDto {

    @NotBlank(message = "Full name is required")
    @Schema(description = "Full name of the user")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(description = "Password is required")
    private String password;


}
