package com.aledev.avaliacaotecnica.v1.repository;


import com.aledev.avaliacaotecnica.v1.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
