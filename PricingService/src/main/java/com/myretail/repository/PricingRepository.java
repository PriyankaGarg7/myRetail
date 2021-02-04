package com.myretail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myretail.model.Price;

@Repository
public interface PricingRepository extends JpaRepository<Price, Long> {

    @Query("FROM Price p where p.productId = :productId	")
    Optional<Price> findPriceByProductId(@Param("productId") Long productId);
}
