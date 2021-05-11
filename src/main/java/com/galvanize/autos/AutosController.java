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

        if (color != null && owner != null && make!=null){
            autosList = autosService.getAutos(color,owner,make);
        }else if (color != null && owner == null && make==null){
            autosList = autosService.getAutos(color);
        }else if (color == null && owner == null && make != null) {
            autosList = autosService.getAutos(make);
        }else if (color == null && owner != null && make == null){
            autosList = autosService.getAutos(owner);
        } else if (color != null && owner != null){
            autosList = autosService.getAutos(color, owner);
        } else if (color != null) {
            autosList = autosService.getAutos(color, make);
        } else if (owner != null) {
            autosList = autosService.getAutos(owner, make);
        }else {
            autosList = autosService.getAutos();
        }

//        int sum = 0;
//        if(color!=null){
//            sum++;
//        }
//        if(make!=null){
//            sum++;
//        }
//        if(owner!=null){
//            sum++;
//        }
//        switch (sum){
//            case 1 : autosList = autosService.getAutos(make);
//                break;
//            case 2 : autosList = autosService.getAutos(color, owner);
//                break;
//            case 3 : autosList = autosService.getAutos(color,owner,make);
//                break;
//            default : autosList = autosService.getAutos();
//        }

        return autosList.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(autosList);
    }

}
