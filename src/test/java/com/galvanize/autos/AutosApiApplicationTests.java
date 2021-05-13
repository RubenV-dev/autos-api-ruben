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

    @Test
    @DisplayName("GET by color returns all of that color")
    void searchByColor(){
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?color=blood red", AutosList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAutomobiles().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("GET by make returns all of that make")
    void searchByMake(){
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?make=Toyota", AutosList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAutomobiles().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("GET by owner returns all of that owner")
    void searchByModel(){
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?owner=ruben", AutosList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAutomobiles().size()).isEqualTo(2);
    }
    /// need 4 more filter tests
    @Test
    @DisplayName("GET by color and make returns all of that color and make")
    void searchByColorAndMake(){
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?color=blood red&make=Honda", AutosList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
//                for(Automobile auto: response.getBody().getAutomobiles()){
//            System.out.println("color =" + auto.getColor() + ", model = " + auto.getModel());
//        }
        assertThat(response.getBody().getAutomobiles().size()).isEqualTo(2);
    }
    @Test
    @DisplayName("GET by make and owner returns all of that make and owner")
    void searchByMakeAndOwner(){
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?make=Honda&owner=ruben", AutosList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isNotNull();
//        for(Automobile auto: response.getBody().getAutomobiles()){
//            System.out.println("model =" + auto.getModel() + ", owner = " + auto.getOwner());
//        }
        assertThat(response.getBody().getAutomobiles().size()).isEqualTo(1);
    }
    @Test
    @DisplayName("GET by owner and color returns all of that owner and color")
    void searchByOwnerAndColor(){
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?owner=ruben&color=blood red", AutosList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAutomobiles().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("GET by color and owner and make returns all of those specifications")
    void searchByColorAndOwnerAndMake(){
        ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos?owner=ruben&color=blood red&make=Honda", AutosList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAutomobiles().size()).isEqualTo(1);
    }


    /// patch (need to read article)
    /// delete

}