package ru.lanit.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lanit.model.Car;
import ru.lanit.model.Person;
import ru.lanit.model.PersonWithCars;
import ru.lanit.model.Statistics;
import ru.lanit.service.PersonsAndCarsService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class PersonsAndCarsController {

    private PersonsAndCarsService service;

    @PostMapping("/person")
    public Person savePerson(@Valid @RequestBody Person person) {
        return service.savePerson(person);
    }

    @PostMapping("/car")
    public Car saveCar(@Valid @RequestBody Car car) {
        return service.saveCar(car);
    }

    @GetMapping("/personWithCars")
    public PersonWithCars getPersonWithCarsById(@Valid @RequestParam long personId) {
        return service.getPersonWithCarsById(personId);
    }

    @GetMapping("/statistics")
    public Statistics getStatistics() {
        return service.getStatistics();
    }

    @GetMapping("/clear")
    public String clear() {
        service.clear();
        return "deleted";
    }
}