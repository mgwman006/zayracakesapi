package zeyracakes.co.tz.Models.Responses;

public record CustomerDetailsDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
