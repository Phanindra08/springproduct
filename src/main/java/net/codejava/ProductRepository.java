package net.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	@Query("select u from Product u where u.code = :search or u.name = :search")
	Product findByCodeorName(String search);
	
	@Query("select u from Product u where u.code = :code")
	Product findByCode(String code);
}
