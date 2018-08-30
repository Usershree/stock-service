package com.sh.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sh.stock.dbservice.model.Quote;

public interface QuotesRepository extends JpaRepository<Quote,Integer>{

	List<Quote> findByUserName(String username);

}
