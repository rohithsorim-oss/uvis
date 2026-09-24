package com.sorim.uvis.dao.repo;

import com.sorim.uvis.dao.entities.Vehicle;
import com.sorim.uvis.dao.entities.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository  extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:search IS NULL OR " +
            "LOWER(v.make) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(v.model) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(v.vin) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(v.licensePlate) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:status IS NULL OR v.status = :status) AND " +
            "(:categoryId IS NULL OR v.category.id = :categoryId) AND " +
            "(:minYear IS NULL OR v.year >= :minYear) AND " +
            "(:maxYear IS NULL OR v.year <= :maxYear)")
    Page<Vehicle> searchVehicles(@Param("search") String search,
                                 @Param("status") VehicleStatus status,
                                 @Param("categoryId") Long categoryId,
                                 @Param("minYear") Integer minYear,
                                 @Param("maxYear") Integer maxYear,
                                 Pageable pageable);
}
