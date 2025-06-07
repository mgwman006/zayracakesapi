package zeyracakes.co.tz.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zeyracakes.co.tz.Common.Enums.UserType;
import zeyracakes.co.tz.Common.Utilities.Result;
import zeyracakes.co.tz.Models.Entities.Users.Customer;
import zeyracakes.co.tz.Models.Entities.Users.User;
import zeyracakes.co.tz.Models.Requests.CutomerRegisterDto;
import zeyracakes.co.tz.Repositories.CustomerRepository;
import zeyracakes.co.tz.Repositories.UserRepository;

import java.util.Optional;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public Result<Customer> registerCustomer(CutomerRegisterDto customerDto)
    {

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerDto.email());
        if (optionalCustomer.isPresent())
            return new Result<>(false, "Customer with given email already exist");

        optionalCustomer = customerRepository.findByPhoneNumber(customerDto.phoneNumber());
        if (optionalCustomer.isPresent())
            return new Result<>(false, "Customer with given phoneNumber already exist");

        Customer newCustomer = new Customer(
                customerDto.firstName(),
                customerDto.lastName(),
                customerDto.email(),
                customerDto.phoneNumber()
        );

        try {
            newCustomer = customerRepository.save(newCustomer);
        } catch(Exception e)
        {
            return new Result<>(false,e.getMessage());
        }

        Optional<User> optionalUser = userRepository.findByEmail(newCustomer.getEmail());
        if (optionalUser.isPresent())
        {
            //Suspicious attempt
            //Send Email Confirmation
            return new Result<>(false,"Suspicious, We sent an OTP,  Please confirm Email");
        }
        optionalUser = userRepository.findByPhoneNumber(newCustomer.getPhoneNumber());
        if(optionalUser.isPresent())
        {
            //Suspicious attempt
            //Send PhoneNumber Confirmation
            return new Result<>(false, "Suspicious, We sent an OTP,  Please confirm PhoneNumber");
        }

        User newUser = new User(
                UserType.Customer,
                newCustomer.getEmail(),
                customerDto.passWord(),
                newCustomer.getPhoneNumber()
        );
        try
        {
            newUser = userRepository.save(newUser);
        }
        catch (Exception e) {
            customerRepository.delete(newCustomer);
            return new Result<>(false,e.getMessage());
        }

        return new Result<>(true, newCustomer);
    }
}
