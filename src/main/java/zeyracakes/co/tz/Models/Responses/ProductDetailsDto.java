package zeyracakes.co.tz.Models.Responses;

import jakarta.persistence.Column;

public record ProductDetailsDto(
        Long id,
        String name,
        String description,
        Long price,
        String imagePath
        )
{
}
