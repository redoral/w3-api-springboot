package com.redoral.w3api.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterService {
    private final MonsterRepository monsterRepository;

    @Autowired
    public MonsterService(MonsterRepository monsterRepository){
        this.monsterRepository = monsterRepository;
    }

    // Returns all monsters
    public List<Monster> getMonsters(){
        return monsterRepository.findAll();
    }


}
