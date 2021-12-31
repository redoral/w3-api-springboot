package com.redoral.w3api.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MonsterService {
    private final MonsterRepository monsterRepository;

    // Init repository to call queries
    @Autowired
    public MonsterService(MonsterRepository monsterRepository){
        this.monsterRepository = monsterRepository;
    }

    // Returns all monsters
    public List<Monster> getMonsters(){
        return monsterRepository.findAll();
    }

    // Gets monsters by type
    public List<Monster> getMonstersByType(String type){
        return monsterRepository.findMonstersByType(type);
    }

    // Get a monster by id
    public Monster getMonster(Long id){
        return monsterRepository.getMonsterById(id);
    }

    // Creates a new monster
    public Monster createMonster(Monster monster){ return monsterRepository.save(monster); }

    // Deletes a monster
    public boolean deleteMonster(Long monsterId){
        try {
            monsterRepository.deleteById(monsterId);
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return false;
        }

        return true;
    }

    // Updates a monster
    @Transactional
    public Monster updateMonster(Long monsterId,
                              Monster monster){

        Monster guy = monsterRepository.findById(monsterId).orElseThrow(() -> new IllegalStateException("Monster with ID: " + monsterId + " does not exist."));

        if (monster.img != null && monster.name != null & monster.type != null & monster.loot!=null){
            guy.setImg(monster.img);
            guy.setName(monster.name);
            guy.setSusceptibility(monster.susceptibility);
            guy.setLoot(monster.loot);

            return monsterRepository.save(guy);
        } else {
            throw new IllegalStateException("All fields must be provided.");
        }
    }
}
