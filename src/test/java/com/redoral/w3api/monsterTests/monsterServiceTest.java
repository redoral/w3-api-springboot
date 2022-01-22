package com.redoral.w3api.monsterTests;


import com.redoral.w3api.exception.MonsterNotFoundException;
import com.redoral.w3api.exception.TypeNotExistsException;
import com.redoral.w3api.monster.Monster;
import com.redoral.w3api.monster.MonsterRepository;
import com.redoral.w3api.monster.MonsterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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
    public void getMonstersByType() throws TypeNotExistsException {
        monsterService.getMonstersByType("Beasts");
        verify(monsterRepository, times(1)).findMonstersByType("Beasts");
    }

    @Test
    public void getMonsterByTypeError() throws TypeNotExistsException {
        when(monsterService.getMonstersByType(any())).thenReturn(null);

        try {
            monsterService.getMonstersByType("Uma");
        } catch (TypeNotExistsException e){
            assertEquals("Type of Uma does not exist.", e.getMessage());
        }

    }

    @Test
    public void getMonsterById() throws MonsterNotFoundException {
        monsterService.getMonster(1L);
        verify(monsterRepository, times(1)).findById(1L);
    }

    @Test
    public void updateMonster() throws MonsterNotFoundException{
        monsterService.updateMonster(1L, testMonster);
        verify(monsterRepository, times(1)).save(testMonster);
    }

    @Test
    public void deleteMonster() throws MonsterNotFoundException{
        monsterService.deleteMonster(1L);
        verify(monsterRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteError() throws MonsterNotFoundException {
        when(monsterService.deleteMonster(1L)).thenThrow(new IllegalStateException());

        boolean result = monsterService.deleteMonster(1L);
        assertEquals(false, result);
    }

    @Test
    public void deleteErrorWhenMonsterNotExists() {
        try {
            monsterService.deleteMonster(5L);
        } catch (MonsterNotFoundException e){
            assertEquals("Monster with ID: 5 does not exist.", e.getMessage());
        }
    }

    @Test
    public void updateError() throws MonsterNotFoundException{
        try {
            monsterService.updateMonster(1L, new Monster(null, null, null, new String[]{""}, new String[]{""}));
        } catch (IllegalStateException e){
            assertEquals("All fields must be provided.", e.getMessage());
        }

        try {
            monsterService.updateMonster(2L, new Monster(null, null, null, new String[]{""}, new String[]{""}));
        } catch (MonsterNotFoundException e){
            assertEquals("Monster with ID: 2 does not exist.", e.getMessage());
        }
    }
}
