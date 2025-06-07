package zeyracakes.co.tz.Common.Dtos;

public record Address(
        String street,
        String city,
        String state,
        String zipCode,
        String country
) {
}
