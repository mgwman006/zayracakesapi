package zeyracakes.co.tz.Controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zeyracakes.co.tz.Common.Utilities.ApiResponse;
import zeyracakes.co.tz.Common.Utilities.Result;
import zeyracakes.co.tz.Models.Entities.Users.Customer;
import zeyracakes.co.tz.Models.Requests.CutomerRegisterDto;
import zeyracakes.co.tz.Models.Responses.CustomerDetailsDto;
import zeyracakes.co.tz.Services.CustomerService;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDetailsDto>> registerCustomer(
            @Valid
            @RequestBody CutomerRegisterDto cutomerRegisterDto
    )
    {
        Result<Customer> result = customerService.registerCustomer(cutomerRegisterDto);
        if (result.isSuccess())
        {
            return ResponseEntity.ok(ApiResponse.success(
                    new CustomerDetailsDto(
                            result.getData().getId(),
                            result.getData().getFirstName(),
                            result.getData().getLastName(),
                            result.getData().getEmail(),
                            result.getData().getPhoneNumber()
                    )
            ));
        }

        return ResponseEntity.badRequest().body(ApiResponse.failure(result.getError()));

    }
}
