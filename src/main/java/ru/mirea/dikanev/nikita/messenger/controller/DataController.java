package ru.mirea.dikanev.nikita.messenger.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.dikanev.nikita.messenger.dto.rest.DataDto;
import ru.mirea.dikanev.nikita.messenger.service.DataService;

@RestController
@RequestMapping(path = "/data")
public class DataController {

    private DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PutMapping("/save")
    public void put(DataDto data){
        dataService.put(data);
    }

}
