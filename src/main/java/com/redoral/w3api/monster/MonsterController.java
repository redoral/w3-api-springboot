package com.redoral.w3api.monster;

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
    public List<Monster> getMonstersByType(@PathVariable("monsterType") String type){ return monsterService.getMonstersByType(type);}

    @GetMapping(path = "{monsterId}")
    public Monster getMonsterById(@PathVariable("monsterId") Long monsterId) { return monsterService.getMonster(monsterId); }

    @PostMapping
    public Monster createMonster(@RequestBody Monster monster){ return monsterService.createMonster(monster); }

    @DeleteMapping(path = "{monsterId}")
    public boolean deleteMonster(@PathVariable("monsterId") Long monsterId){ return monsterService.deleteMonster(monsterId); }

    @PutMapping(path = "{monsterId}")
    public Monster updateMonster(@PathVariable("monsterId") Long monsterId,
                              @RequestBody Monster monster){ return monsterService.updateMonster(monsterId, monster); }

}
