package com.example.back_end.repository;

import com.example.back_end.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "select * from employee where name like concat('%', :search, '%') ", nativeQuery = true)
    Page<Employee> findAllByNameContaining(Pageable pageable,@Param("search") String search);

    @Modifying
    @Transactional
    @Query(value = "insert into employee (name, birth_day, gender, email, phone_number, address, salary, citizen_code, image) " +
            "value(:name, :birth_day, :gender, :email, :phone_number, :address, :salary, :citizen_code, :image)",nativeQuery = true)
    void createEmployee(@Param("name") String name ,
                        @Param("birth_day") String birthDay ,
                        @Param("gender") Integer gender ,
                        @Param("email") String email ,
                        @Param("phone_number") String phoneNumber ,
                        @Param("address") String address ,
                        @Param("salary") String salary ,
                        @Param("citizen_code") String citizenCode ,
                        @Param("image") String image);

}
