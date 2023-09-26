package ru.netology.springboothomework;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.springboothomework.profile.DevProfile;
import ru.netology.springboothomework.profile.ProductionProfile;
import ru.netology.springboothomework.profile.SystemProfile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootHomeworkApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    private static GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);


    @BeforeAll
    public static void  setUp() {
        devapp.start();
        prodapp.start();


    }

    @Test
    void contextLoads() {
        String HOST = "http://localhost:";
        Integer portDev = devapp.getMappedPort(8080);
        Integer portProd = prodapp.getMappedPort(8081);
        SystemProfile devProfile = new DevProfile();
        SystemProfile prodProfile = new ProductionProfile();


        ResponseEntity<String> forEntityDev = restTemplate.getForEntity(HOST + portDev + "/profile", String.class);
        String expected = forEntityDev.getBody();
        String actual = devProfile.getProfile();
        System.out.println("Dev: " + expected);
        Assertions.assertEquals(expected, actual);

        ResponseEntity<String> forEntityProd = restTemplate.getForEntity(HOST + portProd + "/profile", String.class);
        expected = forEntityProd.getBody();
        actual = prodProfile.getProfile();
        System.out.println("Prod " + expected);
        Assertions.assertEquals(expected, actual);


    }

}