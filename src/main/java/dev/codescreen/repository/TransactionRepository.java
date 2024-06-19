package dev.codescreen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.codescreen.entity.transactionentity;

public interface TransactionRepository extends JpaRepository<transactionentity,Long>{
    
}
