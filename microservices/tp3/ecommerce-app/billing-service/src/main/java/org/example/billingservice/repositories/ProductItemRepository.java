package org.example.billingservice.repositories;

import org.example.billingservice.enitites.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource()
public interface ProductItemRepository extends JpaRepository<ProductItem, String> {
}
