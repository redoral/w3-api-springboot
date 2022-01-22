package com.redoral.w3api.monster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long>{
    @Query("SELECT s FROM Monster s WHERE s.type = ?1")
    List<Monster> findMonstersByType(String type);
}
