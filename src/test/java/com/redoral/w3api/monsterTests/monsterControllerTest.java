package com.redoral.w3api.monsterTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redoral.w3api.monster.Monster;
import com.redoral.w3api.monster.MonsterController;
import com.redoral.w3api.monster.MonsterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(MonsterController.class)
public class monsterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private MonsterService monsterService;

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
    public void getMonsterByTypeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/monsters/type/Beasts"));
        verify(monsterService, times(1)).getMonstersByType("Beasts");
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
    public void updateMonsterTest() throws Exception {
        Monster test = new Monster("no img", "Test", "test", new String[]{"test"}, new String[]{"test"});
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/monsters/1")
                .content(objectMapper.writeValueAsString(test))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
