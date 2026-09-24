package com.sorim.uvis.service.impl;

import com.sorim.uvis.controller.PageResponse;
import com.sorim.uvis.controller.VehicleResponse;
import com.sorim.uvis.dao.entities.Vehicle;
import com.sorim.uvis.dao.entities.VehicleStatus;
import com.sorim.uvis.dao.repo.VehicleCategoryRepository;
import com.sorim.uvis.dao.repo.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl {


    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleCategoryRepository vcr, VehicleRepository vr) {
        vehicleRepository = vr;
    }

    public PageResponse<VehicleResponse> getAllVehicles(String search, VehicleStatus status, Long categoryId,
                                                        Integer minYear, Integer maxYear, String sortBy,
                                                        String sortDir, Pageable pageable) {
        String effectiveSearch = (search == null || search.trim().isEmpty()) ? null : search;
        String effectiveSortBy = (sortBy != null && !sortBy.trim().isEmpty()) ? sortBy : "id";
        String effectiveSortDir = (sortDir != null && !sortDir.trim().isEmpty()) ? sortDir : "desc";
        Sort sort = Sort.by(effectiveSortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, effectiveSortBy);
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<Vehicle> vehicles = vehicleRepository.searchVehicles(effectiveSearch, status, categoryId, minYear, maxYear, sortedPageable);
        return PageResponse.of(vehicles.map(this::mapToResponse));
    }
    private VehicleResponse mapToResponse(Vehicle vehicle) {
        VehicleResponse.CategoryResponse categoryResponse = new VehicleResponse.CategoryResponse(
                vehicle.getCategory().getId(),
                vehicle.getCategory().getName(),
                vehicle.getCategory().getCode()
        );

        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getVin(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getLicensePlate(),
                vehicle.getColor(),
                vehicle.getMileage(),
                vehicle.getDailyRentalRate(),
                vehicle.getStatus(),
                vehicle.getImageUrl(),
                categoryResponse,
                vehicle.getCreatedAt()
        );
    }
}
