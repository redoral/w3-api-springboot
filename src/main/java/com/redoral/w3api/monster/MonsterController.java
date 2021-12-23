package com.redoral.w3api.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
