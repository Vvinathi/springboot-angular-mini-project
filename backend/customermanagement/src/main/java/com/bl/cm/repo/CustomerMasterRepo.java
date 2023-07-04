package com.bl.cm.repo;

import com.bl.cm.model.CustomerMaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMasterRepo extends JpaRepository<CustomerMaster, Long> {
	@Query("SELECT MAX(c.custId) FROM CustomerMaster c")
	Long getMaxCustomerId();

	CustomerMaster findByAdharCard(int adharCard);

}
