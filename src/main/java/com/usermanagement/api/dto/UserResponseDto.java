package com.usermanagement.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO for sending user data in responses")
public class UserResponseDto {

    @Schema(description = "unique ID of the user")
    private Long id;

    @Schema(description = "Full name of the user")
    private String fullName;

    @Schema(description = "Email of the user")
    private String email;

    @Schema(description = "Role assigned to user")
    private String role;
}
