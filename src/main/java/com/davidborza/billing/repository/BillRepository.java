package com.davidborza.billing.repository;

import com.davidborza.billing.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
