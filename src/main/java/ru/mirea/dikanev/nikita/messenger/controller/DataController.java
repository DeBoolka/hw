package ru.mirea.dikanev.nikita.messenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.dikanev.nikita.messenger.service.DataService;

@RestController
@RequestMapping(path = "/data")
public class DataController {

    private DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PutMapping()
    public void put(@RequestHeader("Authentication") String token, @RequestBody String data) {
        dataService.put(token, data);
    }

    @GetMapping()
    public String get(@RequestHeader("Authentication") String token) {
        return dataService.get(token);
    }

}
