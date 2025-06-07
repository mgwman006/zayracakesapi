package zeyracakes.co.tz.Models.Requests;


import jakarta.validation.constraints.*;

public record UserLogInDetailsDto(
        @NotBlank(message = "Email is requires")
        String email,
        @NotBlank(message = "password is required")
        String passWord
) {
}
