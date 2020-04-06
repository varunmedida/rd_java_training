package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
