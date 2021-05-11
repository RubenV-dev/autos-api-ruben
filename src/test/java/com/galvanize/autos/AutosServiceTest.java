package com.galvanize.autos;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutosServiceTest {
    private AutosService autosService;

    @Mock
    AutosRepository autosRepository;

    @BeforeEach
    void setup(){
        autosService = new AutosService(autosRepository);
    }

    @Test
    void getAutos() {
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        when(autosRepository.findAll()).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutos();
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty() == false);
    }

    @Test
    void searchAutosByColor() {
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        automobile.setColor("blue");
        when(autosRepository.findByColorContains(anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutos("blue");
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty() == false);
    }

    @Test
    void searchAutosByOwner() {
    }

    @Test
    void searchAutosByMake() {
    }

    @Test
    void addAuto() {
    }

    @Test
    void getAuto() {
    }

    @Test
    void updateAuto() {
    }

    @Test
    void deleteAuto() {
    }
}