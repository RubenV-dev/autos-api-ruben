package com.galvanize.autos;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
        AutosList autoslist = autosService.getAutos();
        assertThat(autoslist).isNotNull();
    }

    @Test
    void testGetAutos() {
    }

    @Test
    void testGetAutos1() {
    }

    @Test
    void testGetAutos2() {
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