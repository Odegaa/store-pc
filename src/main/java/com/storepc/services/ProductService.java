package com.storepc.services;

import com.storepc.models.Product;
import com.storepc.payloads.ProductDto;
import com.storepc.templates.Result;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(Long productId);

    Result addProduct(ProductDto productDto);

    Result updateProduct(Long productId, ProductDto productDto);

    Result deleteProduct(Long productId);

}
