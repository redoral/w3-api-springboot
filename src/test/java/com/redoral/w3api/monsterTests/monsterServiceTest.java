package com.redoral.w3api.monsterTests;


import com.redoral.w3api.monster.Monster;
import com.redoral.w3api.monster.MonsterRepository;
import com.redoral.w3api.monster.MonsterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class monsterServiceTest {

    @Mock
    MonsterRepository monsterRepository;

    @InjectMocks
    private MonsterService monsterService;

    private Monster testMonster = new Monster(1L, "https://static.wikia.nocookie.net/witcher/images/0/07/Tw3_journal_bear.png",
            "Bears", "Beasts", new String[]{"Beast oil", "Quen"}, new String[]{"Bear fat", "Bear hide", "Raw meat", "White bear hide"});

    @BeforeEach
    public void init() {
        when(monsterRepository.findById(1L)).thenReturn(Optional.of(testMonster));
    }

    @Test
    public void createNewMonster(){
        monsterService.createMonster(testMonster);
        verify(monsterRepository, times(1)).save(testMonster);
    }

    @Test
    public void getAllMonsters(){
        monsterService.getMonsters();
        verify(monsterRepository, times(1)).findAll();
    }

    @Test
    public void getMonstersByType(){
        monsterService.getMonstersByType("Beasts");
        verify(monsterRepository, times(1)).findMonstersByType("Beasts");
    }

    @Test
    public void getMonsterById(){
        monsterService.getMonster(1L);
        verify(monsterRepository, times(1)).getMonsterById(1L);
    }

    @Test
    public void updateMonster(){
        monsterService.updateMonster(1L, testMonster);
        verify(monsterRepository, times(1)).save(testMonster);
    }

    @Test
    public void deleteMonster(){
        monsterService.deleteMonster(1L);
        verify(monsterRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteError(){
        when(monsterService.deleteMonster(any())).thenThrow(new IllegalStateException());

        boolean result = monsterService.deleteMonster(5L);
        assertEquals(false, result);
    }

    @Test
    public void updateError(){
        try {
            monsterService.updateMonster(1L, new Monster(null, null, null, new String[]{""}, new String[]{""}));
        } catch (IllegalStateException e){
            assertEquals(e.getMessage(), "All fields must be provided.");
        }
    }
}
