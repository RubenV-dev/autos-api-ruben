package com.galvanize.autos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutosService {

    AutosRepository autosRepository;
    public AutosService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    public AutosList getAutos(){
        return new AutosList(autosRepository.findAll());
    }

    public AutosList getAutos(String color){
        return null;
    }
    public AutosList getAutos(String color, String owner){
        return null;
    }

    public AutosList getAutos(String color, String owner, String make){
        return null;
    }

    public Automobile addAuto(Automobile auto){
        return null;
    }

    public Automobile getAuto(String vin){
        return null;
    }

    public Automobile updateAuto(String vin, String color, String owner){
        return null;
    }

    public void deleteAuto(String vin){
    }

}
