package com.galvanize.autos;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
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
    @DisplayName("Can GET all autos")
    void getAutos() {
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        when(autosRepository.findAll()).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutos();
        assertThat(autoslist).isNotNull();
        assertFalse(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET all can filter by color")
    void searchAutosByColor() {
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        automobile.setColor("blue");
        when(autosRepository.findByColorContains(anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutosByColor("blue");
        assertThat(autoslist).isNotNull();
        assertFalse(autoslist.isEmpty());
    }
    @Test
    @DisplayName("GET an empty array when search by color has no results")
    void searchAutosByColor_ReturnsEmptyArray() {
        Automobile[] automobiles = new Automobile[0];
        when(autosRepository.findByColorContains(anyString())).thenReturn(Arrays.asList(automobiles));
        AutosList autoslist = autosService.getAutosByColor("blue");
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET all can filter owner")
    void searchAutosByOwner() {
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        automobile.setOwner("ruben");
        when(autosRepository.findByOwnerContains(anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutosByOwner("ruben");
        assertThat(autoslist).isNotNull();
        assertFalse(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET an empty array when search by owner has no results")
    void searchAutosByOwner_ReturnsEmptyArray() {
        Automobile[] automobiles = new Automobile[0];
        when(autosRepository.findByOwnerContains(anyString())).thenReturn(Arrays.asList(automobiles));
        AutosList autoslist = autosService.getAutosByOwner("mavi");
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET all can filter by make")
    void searchAutosByMake() {
        Automobile automobile = new Automobile("Toyota", 1994, "Camry", "44444");
        when(autosRepository.findByMakeContains(anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutosByMake("Toyota");
        assertThat(autoslist).isNotNull();
        assertFalse(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET an empty array when search by make has no results")
    void searchAutosByMake_ReturnsEmptyArray() {
        Automobile[] automobiles = new Automobile[0];
        when(autosRepository.findByMakeContains(anyString())).thenReturn(Arrays.asList(automobiles));
        AutosList autoslist = autosService.getAutosByMake("Toyota");
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET all can filter by color and owner")
    void searchAutosByColorAndOwner() {
        Automobile automobile = new Automobile("blue,", "Toyota", 1994, "Camry", "ruben", "44444");
        when(autosRepository.findByColorContainsAndOwnerContains(anyString(), anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutosByColorAndOwner("blue", "ruben");
        assertThat(autoslist).isNotNull();
        assertFalse(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET an empty array when search by color and owner has no results")
    void searchAutosByColorAndOwner_ReturnsEmptyArray() {
        Automobile[] automobiles = new Automobile[0];
        when(autosRepository.findByColorContainsAndOwnerContains(anyString(), anyString())).thenReturn(Arrays.asList(automobiles));
        AutosList autoslist = autosService.getAutosByColorAndOwner("blue", "mavi");
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET all can filter by owner and make")
    void searchAutosByOwnerAndMake() {
        Automobile automobile = new Automobile("blue,", "Toyota", 1994, "Camry", "ruben", "44444");
        when(autosRepository.findByOwnerContainsAndMakeContains(anyString(), anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutosByOwnerAndMake("ruben", "Toyota");
        assertThat(autoslist).isNotNull();
        assertFalse(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET an empty array when search by color and owner has no results")
    void searchAutosByOwnerAndMake_ReturnsEmptyArray() {
        Automobile[] automobiles = new Automobile[0];
        when(autosRepository.findByOwnerContainsAndMakeContains(anyString(), anyString())).thenReturn(Arrays.asList(automobiles));
        AutosList autoslist = autosService.getAutosByOwnerAndMake("mavi", "Toyota");
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET all can filter by color and make")
    void searchAutosByMakeAndColor() {
        Automobile automobile = new Automobile("blue,", "Toyota", 1994, "Camry", "ruben", "44444");
        when(autosRepository.findByMakeContainsAndColorContains(anyString(), anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutosByMakeAndColor("Toyota", "blue");
        assertThat(autoslist).isNotNull();
        assertFalse(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET an empty array when search by make and color has no results")
    void searchAutosByMakeAndColor_ReturnsEmptyArray() {
        Automobile[] automobiles = new Automobile[0];
        when(autosRepository.findByMakeContainsAndColorContains(anyString(), anyString())).thenReturn(Arrays.asList(automobiles));
        AutosList autoslist = autosService.getAutosByMakeAndColor("mavi", "Toyota");
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET all can filter by owner and make")
    void searchAutosByColorAndOwnerAndMake() {
        Automobile automobile = new Automobile("blue,", "Toyota", 1994, "Camry", "ruben", "44444");
        when(autosRepository.findByColorContainsAndOwnerContainsAndMakeContains(anyString(), anyString(), anyString())).thenReturn(Arrays.asList(automobile));
        AutosList autoslist = autosService.getAutosByColorAndOwnerAndMake( "blue", "ruben", "Toyota");
        assertThat(autoslist).isNotNull();
        assertFalse(autoslist.isEmpty());
    }

    @Test
    @DisplayName("GET an empty array when search by color and owner and make has no results")
    void searchAutosByColorAndOwnerAndMake_ReturnsEmptyArray() {
        Automobile[] automobiles = new Automobile[0];
        when(autosRepository.findByColorContainsAndOwnerContainsAndMakeContains(anyString(), anyString(), anyString())).thenReturn(Arrays.asList(automobiles));
        AutosList autoslist = autosService.getAutosByColorAndOwnerAndMake("blue", "mavi", "Toyota");
        assertThat(autoslist).isNotNull();
        assertThat(autoslist.isEmpty());
    }

    @Test
    @DisplayName("add auto in autosservice")
    void addAuto() {
        Automobile automobile = new Automobile("blue,", "Toyota", 1994, "Camry", "ruben", "44444");
        when(autosRepository.save(any(Automobile.class))).thenReturn(automobile);
        Automobile automobile2 = autosService.addAuto( automobile);
        assertThat(automobile2).isNotNull();
        assertThat(automobile2.getMake()).isEqualTo("Toyota");
    }

    @Test
    @DisplayName("GET auto by vin")
    void getAutoByVin() {
        Automobile automobile = new Automobile("blue,", "Toyota", 1994, "Camry", "ruben", "44444");
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.of(automobile));
        Automobile automobile2 = autosService.getAuto(automobile.getVin());
        assertThat(automobile2).isNotNull();
        assertThat(automobile2.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    @DisplayName("update auto in autosservice")
    void updateAuto() {
        Automobile automobile = new Automobile("blue,", "Toyota", 1994, "Camry", "ruben", "44444");
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.of(automobile));
        when(autosRepository.save(any(Automobile.class))).thenReturn(automobile);
        Automobile automobile2 = autosService.updateAuto(automobile.getVin(), "red", "mavi");
        assertThat(automobile2).isNotNull();
        assertThat(automobile2.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    @DisplayName("delete auto by vin in autosservice")
    void deleteAutoByVin() {
        Automobile automobile = new Automobile("blue,", "Toyota", 1994, "Camry", "ruben", "44444");
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.of(automobile));
        autosService.deleteAuto(automobile.getVin());
        verify(autosRepository).delete(any(Automobile.class));
    }

    @Test
    @DisplayName("throw exception when deleting by nonexistent vin")
    void deleteAutoByVin_doesntExist(){
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.empty());
        assertThatExceptionOfType(AutoNotFoundException.class)
                .isThrownBy(() -> autosService.deleteAuto("a fake vin"));
    }
}