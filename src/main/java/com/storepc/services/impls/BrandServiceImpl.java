package com.storepc.services.impls;

import com.storepc.models.Brand;
import com.storepc.models.Product;
import com.storepc.payloads.BrandDto;
import com.storepc.repositories.BrandRepository;
import com.storepc.repositories.ProductRepository;
import com.storepc.services.BrandService;
import com.storepc.templates.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return brandRepository.findById(brandId).orElse(null);
    }

    @Override
    public Result addBrand(BrandDto brandDto) {
        List<Product> productList = new ArrayList<>();

        boolean byBrandName = brandRepository.existsByBrandName(brandDto.getBrandName());
        if (byBrandName) {
            return new Result("This brand name already exist!", false, HttpStatus.CONFLICT);
        }

        Brand brand = new Brand();
        brand.setBrandName(brandDto.getBrandName());
        brand.setAbout(brandDto.getAbout());

        for (Long aLong : brandDto.getProductId()) {
            Optional<Product> optionalProduct = productRepository.findById(aLong);
            if (!optionalProduct.isPresent()) {
                return new Result("Product not found!", false, HttpStatus.NOT_FOUND);
            }
            productList.add(optionalProduct.get());
        }
        brand.setProducts(productList);
        brandRepository.save(brand);
        return new Result("Brand saved!", true, HttpStatus.CREATED);
    }

    @Override
    public Result updateBrand(Long brandId, BrandDto brandDto) {
        List<Product> productList = new ArrayList<>();
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);

        if (!optionalBrand.isPresent()) {
            return new Result("Brand not found!", false, HttpStatus.NOT_FOUND);
        }

        boolean byBrandName = brandRepository.existsByBrandName(brandDto.getBrandName());
        if (byBrandName) {
            return new Result("This brand name already exist!", false, HttpStatus.CONFLICT);
        }

        Brand brand = optionalBrand.get();
        brand.setBrandName(brandDto.getBrandName());
        brand.setAbout(brandDto.getAbout());
        for (Long aLong : brandDto.getProductId()) {
            Optional<Product> optionalProduct = productRepository.findById(aLong);
            if (!optionalProduct.isPresent()) {
                return new Result("Product not found!", false, HttpStatus.NOT_FOUND);
            }
            productList.add(optionalProduct.get());
        }
        brand.setProducts(productList);
        brandRepository.save(brand);
        return new Result("Brand updated!", true, HttpStatus.ACCEPTED);
    }

    @Override
    public Result deleteBrand(Long brandId) {
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);
        if (optionalBrand.isPresent()) {
            brandRepository.deleteById(brandId);
            return new Result("Brand deleted!", true, HttpStatus.ACCEPTED);
        }
        return new Result("Brand not found!", false, HttpStatus.NOT_FOUND);
    }
}