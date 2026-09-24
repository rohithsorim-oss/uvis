package com.sorim.uvis.controller;

import com.sorim.uvis.dao.entities.VehicleStatus;
import com.sorim.uvis.generated.api.v1.controller.VehicleApi;
import com.sorim.uvis.generated.api.v1.model.ApiVehicleRequest;
import com.sorim.uvis.generated.api.v1.model.ApiVehicleResponse;
import com.sorim.uvis.service.impl.VehicleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleController implements VehicleApi {

    private final VehicleServiceImpl vehicleService;


    @Autowired
    public VehicleController(VehicleServiceImpl vs) {
        vehicleService = vs;
    }

    public VehicleController() {
        vehicleService = null;
    }
    @GetMapping("/getVehicles")
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getVehicles(String search, VehicleStatus status, Long categoryId,
                                                                Integer minYear, Integer maxYear, String sortBy,
                                                                String sortDir, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResponse<VehicleResponse> response = vehicleService.getAllVehicles(
                search, status, categoryId, minYear, maxYear, sortBy, sortDir, pageable);

        return ResponseEntity.ok(ApiResponse.success("Vehicles retrieved successfully", response));
    }

    @Override
    public ResponseEntity<Void> createVehicle(ApiVehicleRequest apiVehicleRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<ApiVehicleResponse>> getVehicles(Integer page, Integer size) {
        return null;
    }
}
