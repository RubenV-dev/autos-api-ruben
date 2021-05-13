package com.galvanize.autos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutosApiApplicationTests {

    List<Automobile> testAutos;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    AutosRepository autosRepository;

    @BeforeEach
    void setup(){
        // test data
        this.testAutos = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            this.testAutos.add(new Automobile("Toyota", 1994 + i, "Camry", Integer.toString(i)));
        }
        this.testAutos.add(new Automobile("blood red", "Toyota", 1994, "Camry", "ruben", "4444"));
        this.testAutos.add(new Automobile("blood red", "Honda", 1994, "Civic", "ruben", "444"));
        this.testAutos.add(new Automobile("blood red", "Honda", 1994, "Civic", "mavi", "44"));
        this.testAutos.add(new Automobile("leafy green", "Honda", 1994, "Civic", "mavi", "444444"));
        autosRepository.saveAll(this.testAutos);
    }

    @AfterEach
    void teardown(){
        autosRepository.deleteAll();
    }

	@Test
	void contextLoads() {
	}

	@Test
    @DisplayName("GET all gets all")
    void getAutos_exists_returnsAutosList(){
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos", AutosList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertFalse(response.getBody().isEmpty());
        //// to see them:
//        for(Automobile auto: response.getBody().getAutomobiles()){
//            System.out.println(auto);
//        }
    }

    @Test
    @DisplayName("POST returns new auto details")
    void addAuto_returnsNewAutoDetails(){
        Automobile automobile = new Automobile("purple", "honda", 2020, "accord", "ruben", "8080");
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobile> request = new HttpEntity<>(automobile, headers);
        ResponseEntity<Automobile> response = restTemplate.postForEntity("/api/autos", request, Automobile.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getVin()).isEqualTo(automobile.getVin());
    }

    /// filter gets
    /// patch (need to read article)
    /// delete

}