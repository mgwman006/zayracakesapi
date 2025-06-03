package zeyracakes.co.tz.Models.Requests;

public record UpdateProductMetaDataDto(
        String name,
        String description,
        Long price,
        String imagePath
) {
}
