package com.redoral.w3api.monsterTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redoral.w3api.exception.MonsterNotFoundException;
import com.redoral.w3api.exception.TypeNotExistsException;
import com.redoral.w3api.monster.Monster;
import com.redoral.w3api.monster.MonsterController;
import com.redoral.w3api.monster.MonsterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(MonsterController.class)
public class monsterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private MonsterService monsterService;

    MonsterController monsterController;

    @Test
    public void getAllMonstersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/monsters").contentType(MediaType.APPLICATION_JSON));
        verify(monsterService, times(1)).getMonsters();
    }

    @Test
    public void getMonsterByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/monsters/1").contentType(MediaType.APPLICATION_JSON));
        verify(monsterService, times(1)).getMonster(1L);
    }

    @Test
    public void getMonsterByIdExceptionTest() throws Exception {
        when(mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/monsters/1")
                .contentType(MediaType.APPLICATION_JSON)))
                .thenThrow(new MonsterNotFoundException("Monster with ID: 1 does not exist."));

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/monsters/1").contentType(MediaType.APPLICATION_JSON));
        } catch (MonsterNotFoundException e){
            assertEquals("Monster with ID: 1 does not exist", e.getMessage());
        }
    }

    @Test
    public void getMonstersByTypeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/monsters/type/Beasts"));
        verify(monsterService, times(1)).getMonstersByType("Beasts");
    }

    @Test
    public void getMonstersByTypeExceptionTest() throws Exception {
        when(mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/monsters/type/Uma")))
                .thenThrow(new TypeNotExistsException("Type of Uma does not exist"));

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/monsters/type/Uma"));
        } catch (TypeNotExistsException e){
            assertEquals("Type of Uma does not exist", e.getMessage());
        }
    }

    @Test
    public void createNewMonsterTest() throws Exception {
        Monster test = new Monster(1L, "no img", "Test", "test", new String[]{"test"}, new String[]{"test"});

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/monsters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(test)));

        verify(monsterService, times(1)).createMonster(any(Monster.class));
    }

    @Test
    public void deleteMonsterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/monsters/1"));

        verify(monsterService, times(1)).deleteMonster(1L);
    }


    @Test
    public void deleteMonsterExceptionTest() throws Exception {
        when(mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/monsters/1")))
                .thenThrow(new MonsterNotFoundException("Monster with ID: 1 does not exist."));

        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/monsters/1"));
        } catch (MonsterNotFoundException e){
            assertEquals("Monster with ID: 1 does not exist.", e.getMessage());
        }

    }

    @Test
    public void updateMonsterTest() throws Exception {
        Monster test = new Monster("no img", "Test", "test", new String[]{"test"}, new String[]{"test"});
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/monsters/1")
                .content(objectMapper.writeValueAsString(test))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateMonsterExceptionTest() throws Exception {
        Monster test = new Monster("no img", "Test", "test", new String[]{"test"}, new String[]{"test"});

        when(monsterService.updateMonster(anyLong(), any(Monster.class)))
                .thenThrow(new MonsterNotFoundException("Monster with ID: 1 does not exist."));

        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/monsters/1")
                    .content(objectMapper.writeValueAsString(test))
                    .contentType(MediaType.APPLICATION_JSON));
        } catch (MonsterNotFoundException e){
            assertEquals("Monster with ID: 1 does not exist.", e.getMessage());
        }
    }
}
