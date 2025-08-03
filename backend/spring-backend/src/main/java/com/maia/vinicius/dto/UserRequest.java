package com.maia.vinicius.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;
}
