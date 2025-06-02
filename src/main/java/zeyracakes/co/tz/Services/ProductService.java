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


            // Get the object
//            Blob blob = storage.get(bucketName, objectName);
//            blob.getMetadata().get("");




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
}
