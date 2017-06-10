package org.sid.dao;

import java.util.List;

import org.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
	@Query("SELECT p FROM Produit p WHERE p.designation LIKE :x")
	public Page<Produit> chercher(@Param("x")String mc, Pageable pageable);
	public List<Produit> findByDesignation(String des);
	public Page<Produit> findByDesignation(String des, Pageable pageable);

}
