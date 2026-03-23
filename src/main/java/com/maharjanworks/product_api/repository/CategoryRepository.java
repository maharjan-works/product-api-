package com.maharjanworks.product_api.repository;

import com.maharjanworks.product_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
