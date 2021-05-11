package com.galvanize.autos;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutosController {

    AutosService autosService;

    public AutosController(AutosService autosService){
        this.autosService = autosService;
    }

    @GetMapping("/api/autos")
    public ResponseEntity<AutosList> getAutos(@RequestParam(required = false)String color,
                                             @RequestParam(required = false)String owner,
                                            @RequestParam(required = false) String make){
        AutosList autosList;

        if (color == null && owner == null && make==null){
            autosList = autosService.getAutos();
        }else if (color != null && owner == null && make==null){
            autosList = autosService.getAutos(color);
        }else if (color == null && owner == null && make != null){
            autosList = autosService.getAutos(make);
        } else if (color != null && owner != null && make == null){
            autosList = autosService.getAutos(color, owner);
        } else {
            autosList = autosService.getAutos(owner);
        }

        return autosList.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(autosList);
    }

}
