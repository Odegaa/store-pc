package com.storepc.services.impls;

import com.storepc.models.Attachment;
import com.storepc.models.Category;
import com.storepc.models.Product;
import com.storepc.payloads.ProductDto;
import com.storepc.repositories.AttachmentRepository;
import com.storepc.repositories.CategoryRepository;
import com.storepc.repositories.ProductRepository;
import com.storepc.services.ProductService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AttachmentRepository attachmentRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              AttachmentRepository attachmentRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.attachmentRepository = attachmentRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Result addProduct(ProductDto productDto) {

        List<Long> list = new ArrayList<>();
        List<Attachment> attachmentList = new ArrayList<>();

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setAbout(productDto.getAbout());
        product.setPrice(productDto.getPrice());
        product.setCodeForProduct(UUID.randomUUID().toString());

        for (Long aLong : productDto.getAttachmentId()) {
            list.add(aLong);
            for (Long value : list) {
                Optional<Attachment> optionalAttachment = attachmentRepository.findById(value);
                if (!optionalAttachment.isPresent()) {
                    return new Result("Attachment not found!", false, HttpStatus.NOT_FOUND);
                }
                attachmentList.add(optionalAttachment.get());
                product.setAttachments(attachmentList);
            }
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("Category not found!", false, HttpStatus.NOT_FOUND);
        }
        product.setCategory(optionalCategory.get());
        productRepository.save(product);
        return new Result("Product saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateProduct(Long productId, ProductDto productDto) {
        List<Long> list = new ArrayList<>();
        List<Attachment> attachmentList = new ArrayList<>();

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            return new Result("Product not found!", false, HttpStatus.NOT_FOUND);
        }

        Product product = optionalProduct.get();
        product.setProductName(productDto.getProductName());
        product.setAbout(productDto.getAbout());
        product.setPrice(productDto.getPrice());
        product.setCodeForProduct(UUID.randomUUID().toString());

        for (Long aLong : productDto.getAttachmentId()) {
            list.add(aLong);
            for (Long value : list) {
                Optional<Attachment> optionalAttachment = attachmentRepository.findById(value);
                if (!optionalAttachment.isPresent()) {
                    return new Result("Attachment not found!", false, HttpStatus.NOT_FOUND);
                }
                attachmentList.add(optionalAttachment.get());
                product.setAttachments(attachmentList);
            }
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("Category not found!", false, HttpStatus.NOT_FOUND);
        }
        product.setCategory(optionalCategory.get());
        productRepository.save(product);
        return new Result("Product updated!", true, HttpStatus.ACCEPTED);
    }

    @Override
    public Result deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(productId);
            return new Result("Product deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Product not found!", false, HttpStatus.NOT_FOUND);
    }
}