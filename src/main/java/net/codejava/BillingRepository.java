package net.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillingRepository extends JpaRepository<Billing, String>{
	
	@Query("select u from Billing u where u.bcode = :code")
	Billing findByCode(String code);
	
}
