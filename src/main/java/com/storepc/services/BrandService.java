package com.storepc.services;

import com.storepc.models.Brand;
import com.storepc.payloads.BrandDto;
import com.storepc.templates.Result;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrands();

    Brand getBrand(Long brandId);

    Result addBrand(BrandDto brandDto);

    Result updateBrand(Long brandId, BrandDto brandDto);

    Result deleteBrand(Long brandId);

}
