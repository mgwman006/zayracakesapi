package zeyracakes.co.tz.Services;

import org.springframework.stereotype.Service;
import zeyracakes.co.tz.Common.Utilities.Result;
import zeyracakes.co.tz.Models.Entities.Users.User;
import zeyracakes.co.tz.Models.Requests.UserLogInDetailsDto;
import zeyracakes.co.tz.Repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Result<User> logIn(UserLogInDetailsDto logInDetails)
    {
        Optional<User> optionalUser = userRepository.findByEmail(logInDetails.email());
        if(optionalUser.isEmpty())
            return new Result<>(false,"User Does not exist");
        if (!optionalUser.get().getPassWord().equals(logInDetails.passWord()))
            return  new Result<>(false,"password is incorrect");
        return new Result<>(true, optionalUser.get());
    }
}
