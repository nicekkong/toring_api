package com.cplanet.toring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootTest
@Profile("local")
class ToringApplicationTests {

    @Test
    void contextLoads() {
    }


    @Value("${login.url.patterns}")
    List<String> loginUrls;

    @Test
    public void testP() {

        loginUrls.forEach(System.out::println);

    }

}
