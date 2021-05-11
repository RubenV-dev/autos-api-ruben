package com.galvanize.autos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutosController.class)
public class AutosControllerTest {

    @MockBean
    AutosService autosService;

    ObjectMapper mapper = new ObjectMapper();

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

        AutosList actual = new AutosList(autosList);
        when(autosService.getAutos()).thenReturn(actual);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    // GET: /api/autos no autos in database returns 204 no content
    @Test
    void getAuto_noParams_none_returnsNoContent() throws Exception {
        when(autosService.getAutos()).thenReturn(new AutosList());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // GET: /api/autos?color=blue returns all blue cars
    @Test
    @DisplayName("GET all can filter by color")
    void getAll_canFilterByColor_returnsBlue() throws Exception {
        List<Automobile> autosList = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            autosList.add(new Automobile("Toyota", 1994, "Camry", "44444"));
        }
        AutosList actual = new AutosList(autosList);
        when(autosService.getAutos(anyString())).thenReturn(actual);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos?color=blue"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(6)));
    }
    // GET: /api/autos?make=toyota returns all toyotas
    @Test
    @DisplayName("GET all can filter by make")
    void getAll_canFilterByMake_returnsAllCarsFilteredByMake() throws Exception {
        List<Automobile> autosList = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            autosList.add(new Automobile("Toyota", 1994, "Camry", "44444"));
        }
        AutosList actual = new AutosList(autosList);
        when(autosService.getAutos(anyString())).thenReturn(actual);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos?make=toyota"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(8)));
    }

    @Test
    @DisplayName("GET all can filter by owner")
    void getAll_canFilterByMake_returnsAllCarsFilteredByOwner() throws Exception {
        List<Automobile> autosList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            autosList.add(new Automobile("Toyota", 1994, "Camry", "44444"));
        }
        AutosList actual = new AutosList(autosList);
        when(autosService.getAutos(anyString())).thenReturn(actual);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos?owner=mavi"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(4)));
    }
    // GET: /api/autos?color=blue&owner=Ruben returns all blue cars with owner ruben
    @Test
    @DisplayName("GET all can filter by color and owner")
    void getAllCanFilterByColorAndOwner() throws Exception {
        List<Automobile> autosList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            autosList.add(new Automobile("Toyota", 1994, "Camry", "44444"));
        }
        AutosList actual = new AutosList(autosList);
        when(autosService.getAutos(anyString(),anyString())).thenReturn(actual);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos?color=blue&&owner=ruben"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(4)));
    }

    @Test
    @DisplayName("GET all can filter by color and make")
    void getAllCanFilterByColorAndMake() throws Exception {
        List<Automobile> autosList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            autosList.add(new Automobile("Toyota", 1994, "Camry", "44444"));
        }
        AutosList actual = new AutosList(autosList);
        when(autosService.getAutos(anyString(),anyString())).thenReturn(actual);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos?color=blue&&make=crystler"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(4)));
    }

    @Test
    @DisplayName("GET all can filter by owner and make")
    void getAllCanFilterByOwnerAndMake() throws Exception {
        List<Automobile> autosList = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            autosList.add(new Automobile("Toyota", 1994, "Camry", "44444"));
        }
        AutosList actual = new AutosList(autosList);
        when(autosService.getAutos(anyString(),anyString())).thenReturn(actual);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos?owner=mavi&&make=crystler"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(2)));
    }

    @Test
    @DisplayName("GET all can filter by color and owner and make")
    void getAllCanFilterByColorAndOwnerAndMake() throws Exception {
        List<Automobile> autosList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            autosList.add(new Automobile("Toyota", 1994, "Camry", "44444"));
        }
        AutosList actual = new AutosList(autosList);
        when(autosService.getAutos(anyString(),anyString(),anyString())).thenReturn(actual);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos?color=blue&&owner=ruben&&make=toyota"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(4)));
    }

    // POST: /api/autos adds automobile & returns it when given correct params (200)
    @Test
    @DisplayName("can POST new car")
    void addAuto_valid_returnsAuto() throws Exception{
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        when(autosService.addAuto(any(Automobile.class))).thenReturn(automobile);

        mockMvc.perform(post("/api/autos").contentType(MediaType.APPLICATION_JSON)
                                            .content(mapper.writeValueAsString(automobile)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("make").value("Toyota"));
    }

    // GET: /api/autos/{vin} returns auto by VIN (id) number
    @Test
    @DisplayName("can GET single car by VIN")
    void getCarByVin() throws Exception {
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");

        when(autosService.getAuto(anyString())).thenReturn(automobile);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/autos/" + automobile.getVin()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("vin")
                        .value(automobile.getVin()));
    }


    // PATCH: /api/autos/{vin} returns patched automobile with supplied vin
    @Test
    @DisplayName("can PATCH existing car")
    void updateAuto() throws Exception{
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        when(autosService.updateAuto(anyString(),anyString(),anyString())).thenReturn(automobile);
        String expectedData = "{\"color\": \"red\", \"owner\": \"ruben\"}";
        mockMvc.perform(patch("/api/autos/" + automobile.getVin()).contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("color").value("red"))
                .andExpect(jsonPath("owner").value("ruben"));
    }

    // DELETE: /api/autos/{vin} deletes auto with specified VIN (202)
    @Test
    @DisplayName("can DELETE existing car")
    void deleteAuto_withVin_exists_returns202() throws Exception{
        mockMvc.perform(delete("/api/autos/aabbcc"))
                .andExpect(status().isAccepted());

        verify(autosService).deleteAuto(anyString());
    }


    /////// STRETCHES
    // POST: /api/autos bad params returns 400 (bad request)
    @Test
    @DisplayName("cannot POST invalid car")
    void addAuto_invalid_returnsBadRequest() throws Exception {
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        when(autosService.addAuto(any(Automobile.class))).thenThrow(InvalidAutoException.class);

        mockMvc.perform(post("/api/autos").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(automobile)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
        // GET: /api/autos/{vin} returns 204 (no content) when no vehicle with that vin is found
        @Test
        @DisplayName("GET car by vin returns 204 when no such car exists")
        void getCarByVin_invalidVin_noContentError() throws Exception {
               doThrow(new AutoNotFoundException()).when(autosService).getAuto(anyString());
               mockMvc.perform(get("/api/autos/aabbcc"))
                       .andExpect(status().isNoContent());

        }

    // PATCH: /api/autos/{vin} returns 204 (no content) when no vehicle with that vin is found
    // PATCH: /api/autos/{vin} returns 400 (bad request) when no payload, no changes or already done

    // DELETE: /api/autos/{vin} 204 (no content) when there is no vehicle with that VIN
    @Test
    @DisplayName("attempt to DELETE nonexistent car")
    void deleteAuto_withVin_doesNotExist_returnsNoContent() throws Exception{
        doThrow(new AutoNotFoundException()).when(autosService).deleteAuto(anyString());
        mockMvc.perform(delete("/api/autos/aabbcc"))
                .andExpect(status().isNoContent());

    }
}
