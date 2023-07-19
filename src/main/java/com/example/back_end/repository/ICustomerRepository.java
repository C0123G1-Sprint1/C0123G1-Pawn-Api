package com.example.back_end.repository;

import com.example.back_end.dto.ICustomerDto;
import com.example.back_end.model.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customers, Long> {
    @Query(value = "SELECT c.id,c.name as name,c.citizen_code as citizenCode,c.quantity_contract as quantityContract FROM customers as c where is_delete=false ",
            nativeQuery = true)
    Page<ICustomerDto> findByCustomer(Pageable pageable);

    @Query(value = "SELECT c.id as id,c.name as name,c.citizen_code as citizenCode,c.quantity_contract as quantityContract FROM customers as c WHERE c.name LIKE concat('%',:name,'%') AND is_delete=false",
            nativeQuery = true)
    Page<ICustomerDto> searchCustomer(Pageable pageable, @Param("name") String name);

    @Query(value = "SELECT c.id as customerId,c.name as name,c.citizen_code as citizenCode, c.quantity_contract as quantityContract FROM customers as c WHERE c.id=:id", nativeQuery = true)
    ICustomerDto findByIdCustomer(@Param("id") Long customerId);
}
