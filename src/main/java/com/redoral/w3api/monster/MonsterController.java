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

    @GetMapping(path = "{monsterType}")
    public List<Monster> getMonstersByType(@PathVariable("monsterType") String type){ return monsterService.getMonstersByType(type);}

    @PostMapping
    public void createMonster(@RequestBody Monster monster){ monsterService.createMonster(monster); }

    @DeleteMapping(path = "{monsterId}")
    public void deleteMonster(@PathVariable("monsterId") Long monsterId){ monsterService.deleteMonster(monsterId); }

    @PutMapping(path = "{monsterId}")
    public void updateMonster(@PathVariable("monsterId") Long monsterId,
                              @RequestBody Monster monster){ monsterService.updateMonster(monsterId, monster); }

}
