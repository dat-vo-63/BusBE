package com.bus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bus.demo.entity.Bill;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long>{
public Bill findByBillId(long billId);
@Transactional
@Modifying(clearAutomatically = true)
@Query("DELETE FROM Bill  where billId =:billId")
public int deleteByBillId(@Param ("billId") long billId);
}
