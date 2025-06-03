package zeyracakes.co.tz.Services;

import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zeyracakes.co.tz.Models.Entities.Product;
import zeyracakes.co.tz.Models.Requests.AddProductDto;
import zeyracakes.co.tz.Models.Responses.ProductDetailsDto;
import zeyracakes.co.tz.Repositories.ProductRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDetailsDto addProduct(AddProductDto productDto)
    {
        if (productDto.image().isEmpty())
            throw new RuntimeException("Please Upload File Data");

        try {

            String projectId = "tante-461318";
            String bucketName = "zayracakes";
            String objectName = "productimages/"+productDto.image().getOriginalFilename();

            BlobId blobId = BlobId.of(bucketName, objectName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            Storage storage = StorageOptions.getDefaultInstance().getService();
            storage.create(blobInfo, productDto.image().getBytes());


            String publicUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, objectName);

            Product newProduct = new Product(
                    productDto.name(),
                    productDto.description(),
                    productDto.price(),
                    publicUrl
            );

            newProduct = productRepository.save(newProduct);

            return new ProductDetailsDto(
                    newProduct.getId(),
                    newProduct.getName(),
                    newProduct.getDescription(),
                    newProduct.getPrice(),
                    newProduct.getImagePath()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<ProductDetailsDto> getAllProducts()
    {
        return productRepository.findAll().stream().map(p -> new ProductDetailsDto(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getImagePath()
        )).toList();
    }

    public void deleteProduct(Long productId)
    {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new RuntimeException("Product with id "+productId+" not exist");

        try
        {
            productRepository.delete(optionalProduct.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
