package zeyracakes.co.tz.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zeyracakes.co.tz.Models.Requests.AddProductDto;
import zeyracakes.co.tz.Models.Responses.ProductDetailsDto;
import zeyracakes.co.tz.Services.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDetailsDto> addProduct(
            @RequestParam MultipartFile image,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long price
    )
    {

        ProductDetailsDto addedProduct = productService.addProduct(
                new AddProductDto(
                        name,
                        description,
                        price,
                        image
                )
        );

        URI location = URI.create("landlords/"+addedProduct.id());
        return ResponseEntity.created(location).body(addedProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailsDto>> getAllProducts()
    {
        List<ProductDetailsDto> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }
}
