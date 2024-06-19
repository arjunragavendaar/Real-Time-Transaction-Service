package dev.codescreen.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.codescreen.entity.userentity;

public interface BankingRepository extends JpaRepository<userentity,Long>{

    @Query(value = "SELECT * FROM userdata WHERE user_Id = :userId", nativeQuery = true)
    userentity findByUserId(@Param("userId") String userId);
}
