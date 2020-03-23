package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
