package com.storepc.services;

import com.storepc.models.address.Region;
import com.storepc.payloads.RegionDto;
import com.storepc.templates.Result;

import java.util.List;

public interface RegionService {

    List<Region> getAllRegions();

    Region getRegion(Long regionId);

    Result addRegion(RegionDto regionDto);

    Result updateRegion(Long regionId, RegionDto regionDto);

    Result deleteRegion(Long regionId);

}
