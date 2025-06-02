package zeyracakes.co.tz.Models.Requests;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public record AddProductDto(
        String name,
        String description,
        Long price,
        MultipartFile image
) {
}
