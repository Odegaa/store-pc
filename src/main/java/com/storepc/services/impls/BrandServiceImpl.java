package com.storepc.services.impls;

import com.storepc.models.Brand;
import com.storepc.payloads.BrandDto;
import com.storepc.repositories.BrandRepository;
import com.storepc.repositories.ProductRepository;
import com.storepc.services.BrandService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository,
                            ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(Long brandId) {
        return null;
    }

    @Override
    public Result addBrand(BrandDto brandDto) {
        return null;
    }

    @Override
    public Result updateBrand(Long brandId, BrandDto brandDto) {
        return null;
    }

    @Override
    public Result deleteBrand(Long brandId) {
        return null;
    }
}