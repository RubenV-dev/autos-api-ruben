package com.galvanize.autos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return new AutosList();
    }

    public AutosList getAutosByOwner(String owner){
        List<Automobile> automobiles = autosRepository.findByOwnerContains(owner);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return new AutosList();
    }

    public AutosList getAutosByMake(String make){
        List<Automobile> automobiles = autosRepository.findByMakeContains(make);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return new AutosList();
    }

    public AutosList getAutosByColorAndOwner(String color, String owner){
        List<Automobile> automobiles = autosRepository.findByColorContainsAndOwnerContains(color, owner);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return new AutosList();
    }

    public AutosList getAutosByOwnerAndMake(String owner, String make){
        List<Automobile> automobiles = autosRepository.findByOwnerContainsAndMakeContains(owner, make);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return new AutosList();
    }

    public AutosList getAutosByMakeAndColor(String make, String color){
        List<Automobile> automobiles = autosRepository.findByMakeContainsAndColorContains(make, color);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return new AutosList();
    }

    public AutosList getAutosByColorAndOwnerAndMake(String color, String owner, String make){
        List<Automobile> automobiles = autosRepository.findByColorContainsAndOwnerContainsAndMakeContains(color, owner, make);
        if(!automobiles.isEmpty()){
            return new AutosList(automobiles);
        }
        return new AutosList();
    }

    public Automobile addAuto(Automobile auto){
        return autosRepository.save(auto);
    }

    public Automobile getAuto(String vin){
        return autosRepository.findByVin(vin).orElse(null);
    }

    public Automobile updateAuto(String vin, String color, String owner){
        Optional<Automobile> oAuto = autosRepository.findByVin(vin);
        if(oAuto.isPresent()){
            oAuto.get().setColor(color);
            oAuto.get().setOwner(owner);
            return autosRepository.save(oAuto.get());
        }
        return null;
    }

    public void deleteAuto(String vin){
        Optional<Automobile> oAuto = autosRepository.findByVin(vin);
        if(oAuto.isPresent()){
            autosRepository.delete(oAuto.get());
            //automobile.ifPresent(value -> autosRepository.deleteById(value.getId()))
        } else {
            throw new AutoNotFoundException();
        }
    }

}
