package com.galvanize.autos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutosController.class)
public class AutosControllerTest {

    @MockBean
    AutosService autosService;

    @Autowired
    MockMvc mockMvc;

    // GET: /api/autos returns list of all autos in database (200)
    @Test
    @DisplayName("GET /autos returns all autos")
    void getAllAutos() throws Exception{
        List<Automobile> autosList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            autosList.add(new Automobile("Toyota", 1994, "Camry", "44444"));
        }

//        String expectedData = "{{\"color\": \"red\", \"make\": \"honda\"},{\"color\": \"red\", \"make\": \"honda\"},{\"color\": \"red\", \"make\": \"honda\"},{\"color\": \"red\", \"make\": \"honda\"},{\"color\": \"red\", \"make\": \"honda\"}}";

        AutosList actual = new AutosList(autosList);

        when(autosService.getAutos()).thenReturn(actual);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }
    //// CURRENTLY IN PROGRESS
    // GET: /api/autos?color=blue returns all blue cars
//    @Test
//    @DisplayName("GET all can filter by color")
//    void GetAllCanFilterByColor() throws Exception {
//        List<Automobile> autosList = new ArrayList<>();
//        for(int i = 0; i < 4; i++){
//            autosList.add(new Automobile("blue", "Toyota", 1994, "Camry", "44444"));
//        }
//
//        AutosList actual = new AutosList(autosList);
//
//        when(autosService.getAutos(anyString())).thenReturn(actual);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos?color=BLUE"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.automobiles", hasSize(4)));
//    }

    // GET: /api/autos?make=toyota returns all toyotas
    // GET: /api/autos?color=blue&make=toyota returns all blue toyotas

    // POST: /api/autos adds automobile & returns it when given correct params (200)

    // GET: /api/autos/{vin} returns auto by VIN (id) number

    // PATCH: /api/autos/{vin} returns patched automobile with supplied vin

    // DELETE: /api/autos/{vin} deletes auto with specified VIN (202)

    /////// STRETCHES

    // GET: /api/autos no autos in database returns 204 no content
//    @Test
//    @DisplayName("GET /autos with empty database returns 204 error")
//    void getAllAutosErrorNoAutos() throws Exception{
//
//        AutosList actual = new AutosList();
//
//        when(autosService.getAutos()).thenReturn(actual);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos"))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
////        AutosList emptyList = new AutosList();
////        doThrow(new RuntimeException("No automobiles in database")).when(emptyList);

    // POST: /api/autos bad params returns 400 (bad request)

    // GET: /api/autos/{vin} returns 204 (no content) when no vehicle with that vin is found

    // PATCH: /api/autos/{vin} returns 204 (no content) when no vehicle with that vin is found
    // PATCH: /api/autos/{vin} returns 400 (bad request) when no payload, no changes or already done

    // DELETE: /api/autos/{vin} 204 (no content) when there is no vehicle with that VIN
}
