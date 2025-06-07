package zeyracakes.co.tz.Models.Responses;

import zeyracakes.co.tz.Common.Enums.UserType;

public record UserDetailsDto(
        Long id,
        UserType userType,
        String email,
        String phoneNumber,
        String passWord
) {
}
