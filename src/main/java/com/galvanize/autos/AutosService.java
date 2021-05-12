package com.galvanize.autos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutosService {

    AutosRepository autosRepository;
    public AutosService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    public AutosList getAutos(){
        return new AutosList(autosRepository.findAll());
    }

    public AutosList getAutosByColor(String color){
        List<Automobile> automobiles = autosRepository.findByColorContains(color);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return null;
    }

    public AutosList getAutosByOwner(String owner){
        List<Automobile> automobiles = autosRepository.findByOwnerContains(owner);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return null;
    }

    public AutosList getAutosByMake(String make){
        List<Automobile> automobiles = autosRepository.findByMakeContains(make);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return null;
    }

    public AutosList getAutosByColorAndOwner(String color, String owner){
        List<Automobile> automobiles = autosRepository.findByColorContainsAndOwnerContains(color, owner);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return null;
    }

    public AutosList getAutosByOwnerAndMake(String owner, String make){
        List<Automobile> automobiles = autosRepository.findByOwnerContainsAndMakeContains(owner, make);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return null;
    }

    public AutosList getAutosByMakeAndColor(String make, String color){
        List<Automobile> automobiles = autosRepository.findByMakeContainsAndColorContains(make, color);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return null;
    }

    public AutosList getAutosByColorAndOwnerAndMake(String color, String owner, String make){
        List<Automobile> automobiles = autosRepository.findByColorContainsAndOwnerContainsAndMakeContains(color, owner, make);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
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
