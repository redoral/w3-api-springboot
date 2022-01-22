package com.redoral.w3api.monster;

import com.redoral.w3api.exception.MonsterNotFoundException;
import com.redoral.w3api.exception.TypeNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/monsters")
public class MonsterController {
    private final MonsterService monsterService;

    @Autowired
    public MonsterController(MonsterService monsterService){
        this.monsterService = monsterService;
    }

    @GetMapping
    public List<Monster> getMonsters(){
        return monsterService.getMonsters();
    }

    @GetMapping(path = "type/{monsterType}")
    public List<Monster> getMonstersByType(@PathVariable("monsterType") String type){
        try {
            return monsterService.getMonstersByType(type);
        } catch (TypeNotExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "{monsterId}")
    public Monster getMonsterById(@PathVariable("monsterId") Long monsterId) {
        try {
            return monsterService.getMonster(monsterId);
        } catch (MonsterNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Monster createMonster(@RequestBody Monster monster){ return monsterService.createMonster(monster); }

    @DeleteMapping(path = "{monsterId}")
    public boolean deleteMonster(@PathVariable("monsterId") Long monsterId){
        try {
            monsterService.deleteMonster(monsterId);
            return true;
        } catch(MonsterNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PutMapping(path = "{monsterId}")
    public Monster updateMonster(@PathVariable("monsterId") Long monsterId, @RequestBody Monster monster){
       try {
           Monster updatedMonster = monsterService.updateMonster(monsterId, monster);
           return updatedMonster;
       } catch (MonsterNotFoundException e){
           System.out.println(e.getMessage());
           return null;
       }
    }

}
