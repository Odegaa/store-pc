package com.storepc.services.impls;

import com.storepc.models.Product;
import com.storepc.payloads.ProductDto;
import com.storepc.repositories.AttachmentRepository;
import com.storepc.repositories.CategoryRepository;
import com.storepc.repositories.ProductRepository;
import com.storepc.services.ProductService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public Result addProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Result updateProduct(Long productId, ProductDto productDto) {
        return null;
    }

    @Override
    public Result deleteProduct(Long productId) {
        return null;
    }
}