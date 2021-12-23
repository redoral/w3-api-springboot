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

    // Creates a new monster
    public void createMonster(Monster monster){ monsterRepository.save(monster); }

    // Deletes a monster
    public void deleteMonster(Long monsterId){
        Monster monster = monsterRepository.findById(monsterId).orElseThrow(() -> new IllegalStateException("Student with ID: " + monsterId + " does not exist."));


        monsterRepository.deleteById(monsterId);
    }

    // Updates a monster
    @Transactional
    public void updateMonster(Long monsterId,
                              Monster monster){

        Monster guy = monsterRepository.findById(monsterId).orElseThrow(() -> new IllegalStateException("Monster with ID: " + monsterId + " does not exist."));

        if (monsterId != null && monster.img != null && monster.name != null & monster.type != null & monster.loot!=null){
            guy.setImg(monster.img);
            guy.setName(monster.name);
            guy.setSusceptibility(monster.susceptibility);
            guy.setLoot(monster.loot);

            monsterRepository.save(guy);
        } else {
            throw new IllegalStateException("All fields must be provided.");
        }
    }
}
