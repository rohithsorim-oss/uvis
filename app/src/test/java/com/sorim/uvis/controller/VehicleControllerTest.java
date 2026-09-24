package com.sorim.uvis.controller;

import com.sorim.uvis.generated.api.v1.model.ApiVehicleRequest;
import com.sorim.uvis.generated.api.v1.model.ApiVehicleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleControllerTest {

    private final VehicleController vehicleController =
            new VehicleController();

    @Test
    void shouldCreateVehicleSuccessfully() {

        ApiVehicleRequest request = new ApiVehicleRequest()
                .vin("MA3EJKD1S00100001")
                .registrationNo("TN01AB1001")
                .make("Toyota")
                .model("Innova Crysta");

        ResponseEntity<Void> response =
                vehicleController.createVehicle(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestWhenVehicleRequestIsNull() {

        ResponseEntity<Void> response =
                vehicleController.createVehicle(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturnVehicleList() {

        ResponseEntity<List<ApiVehicleResponse>> response =
                vehicleController.getVehicles(0, 20);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        ApiVehicleResponse vehicle = response.getBody().get(0);

        assertEquals(1L, vehicle.getVehicleId());
        assertEquals("Toyota", vehicle.getMake());
        assertEquals("Innova Crysta", vehicle.getModel());
        assertEquals("MA3EJKD1S00100001", vehicle.getVin());
    }

    @Test
    void shouldReturnValidVehicleDetails() {

        List<ApiVehicleResponse> vehicles =
                vehicleController.getVehicles(0, 20).getBody();

        assertNotNull(vehicles);

        vehicles.forEach(vehicle -> {
            assertNotNull(vehicle.getVehicleId());
            assertNotNull(vehicle.getVin());
            assertNotNull(vehicle.getRegistrationNo());
            assertNotNull(vehicle.getMake());
            assertNotNull(vehicle.getModel());
        });
    }
}