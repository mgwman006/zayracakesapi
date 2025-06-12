package zeyracakes.co.tz.Services;

import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import zeyracakes.co.tz.Models.Entities.Product;
import zeyracakes.co.tz.Models.Requests.AddProductDto;
import zeyracakes.co.tz.Models.Requests.UpdateProductMetaDataDto;
import zeyracakes.co.tz.Models.Responses.ProductDetailsDto;
import zeyracakes.co.tz.Repositories.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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


            File tempFile = File.createTempFile("upload-", productDto.image().getOriginalFilename());
            productDto.image().transferTo(tempFile);
            String key = saveImageToS3(tempFile.toPath());
            Product newProduct = new Product(
                    productDto.name(),
                    productDto.description(),
                    productDto.price(),
                    key
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

    public String saveImageToS3(Path path) throws IOException {


        String bucketName = "zayracakes";
        String objectName = "productimages/"+path.getFileName();
        final String region = "eu-west-2"; // Replace with your region
        S3Client s3Client = S3Client.builder().region(Region.of(region)).build();



        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(objectName)
        .contentType(Files.probeContentType(path))
        .build();


        try {

            s3Client.putObject(putObjectRequest, path);
            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + objectName;

        } catch (S3Exception e) {
         throw new RuntimeException("Failed to upload image to S3", e);
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

    public ProductDetailsDto updateProductMetaData(Long productId, UpdateProductMetaDataDto productMetaDataDto)
    {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new RuntimeException("Product with id "+productId+" not exist");

        Product product = optionalProduct.get();
        product.updateMetaData(
                productMetaDataDto.name(),
                productMetaDataDto.description(),
                productMetaDataDto.price(),
                productMetaDataDto.imagePath()
        );

        try {
            product =  productRepository.save(product);
            return new ProductDetailsDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getImagePath()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to update product metadata: " + e.getMessage(), e);

        }


    }
}
