package com.redoral.w3api.monster;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MonsterConfig {

    @Bean
    CommandLineRunner commandLineRunner(MonsterRepository monsterRepository) {
        return args -> {
            Monster bears = new Monster(1L, "https://static.wikia.nocookie.net/witcher/images/0/07/Tw3_journal_bear.png",
                    "Bears", "Beast", new String[]{"Beast oil", "Quen"}, new String[]{"Bear fat", "Bear hide", "Raw meat", "White bear hide"});
            Monster bigBadWolf = new Monster(2L,"https://static.wikia.nocookie.net/witcher/images/d/db/Tw3_journal_bigbadwolf.png",
                    "Big Bad Wolf", "Beast", new String[]{"Devil's Puffball", "Quen"}, new String[]{"Magic dust", "Red mutagen", "Fake tooth", "Corkscrew", "Bottlecaps"});

            monsterRepository.saveAll(Arrays.asList(bears, bigBadWolf));
        };
    }
}

