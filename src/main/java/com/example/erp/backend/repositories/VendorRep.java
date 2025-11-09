package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.Vendor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRep extends JpaRepository<Vendor ,Long> {
    Page<Vendor> findByIsActiveTrue(Pageable pageable);

    Optional<Vendor> findByIdAndIsActiveTrue(Long id);


    @Query(value = """
        SELECT * FROM vendors v
        WHERE v.is_active = true
          AND (
            v.name ILIKE :search
            OR v.email ILIKE :search
            OR v.gst_number ILIKE :search
            OR v.contact_no ILIKE :search
            OR v.alter_contact ILIKE :search
            OR v.alter_email ILIKE :search
          )
        """,
            countQuery = """
        SELECT count(v.id) FROM vendors v
        WHERE v.is_active = true
          AND (
            v.name ILIKE :search
            OR v.email ILIKE :search
            OR v.gst_number ILIKE :search
            OR v.contact_no ILIKE :search
            OR v.alter_contact ILIKE :search
            OR v.alter_email ILIKE :search
          )
        """,
            nativeQuery = true)
    Page<Vendor> searchActiveVendors(@Param("search") String search, Pageable pageable);


    boolean existsByEmailAndIsActiveTrue(String email);

    boolean existsByAlterEmailAndIsActiveTrue(String alterEmail);

    boolean existsByGstNoAndIsActiveTrue(@Size(min=15,max = 15 ,message = "Gst Number Should be 15 digit.") String gstNo);

    boolean existsByContactNoAndIsActiveTrue(@NotBlank(message = "Contact No is required.") @Size(min = 10,max=15,message = "Contact should be in between 10 to 15 number.") String contactNo);

    boolean existsByAlterContactAndIsActiveTrue(@Email(message = "Invalid email format") String alterEmail);
}
