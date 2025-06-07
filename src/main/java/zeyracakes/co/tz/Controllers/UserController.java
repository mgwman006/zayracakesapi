package zeyracakes.co.tz.Controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zeyracakes.co.tz.Common.Utilities.ApiResponse;
import zeyracakes.co.tz.Common.Utilities.Result;
import zeyracakes.co.tz.Models.Entities.Users.User;
import zeyracakes.co.tz.Models.Responses.UserDetailsDto;
import zeyracakes.co.tz.Models.Requests.UserLogInDetailsDto;
import zeyracakes.co.tz.Services.UserService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDetailsDto>> logInUser(
            @Valid
            @RequestBody UserLogInDetailsDto userLogInDetails
    )
    {
        Result<User> result = userService.logIn(userLogInDetails);

        if (result.isSuccess())
            return ResponseEntity.ok(ApiResponse.success(
                    new UserDetailsDto(
                            result.getData().getId(),
                            result.getData().getUserType(),
                            result.getData().getEmail(),
                            result.getData().getPhoneNumber(),
                            result.getData().getPassWord()
                    )
            ));

        return ResponseEntity.badRequest().body(ApiResponse.failure(result.getError()));

    }
}
