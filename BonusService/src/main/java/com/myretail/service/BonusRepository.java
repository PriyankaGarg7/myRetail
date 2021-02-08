package com.myretail.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myretail.model.Bonus;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

	@Query("FROM Bonus b where b.productId = :productId	")
	Optional<Bonus> findBonusByProductId(@Param("productId") Long productId);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Bonus b set b.bonus =:bonus where b.productId =:productId")
	void update(@Param("productId") Long productId, BigDecimal bonus);

}
