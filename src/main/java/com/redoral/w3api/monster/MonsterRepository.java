package com.redoral.w3api.monster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long>{}
