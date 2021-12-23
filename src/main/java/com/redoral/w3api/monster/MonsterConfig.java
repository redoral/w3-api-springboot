package com.redoral.w3api.monster;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class MonsterConfig {

    @Bean
    CommandLineRunner commandLineRunner(MonsterRepository monsterRepository) {
        return args -> {
            Monster bears = new Monster("https://static.wikia.nocookie.net/witcher/images/0/07/Tw3_journal_bear.png/revision/latest/scale-to-width-down/654?cb=20160304204324",
                    "Bears", "Beasts", new String[]{"Beast oil", "Quen"}, new String[]{"Bear fat", "Bear hide", "Raw meat", "White bear hide"});

            monsterRepository.saveAll(List.of(bears));
        };
    }
}
