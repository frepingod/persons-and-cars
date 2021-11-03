package ru.lanit.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lanit.exception.InvalidDataException;
import ru.lanit.exception.NotFoundException;
import ru.lanit.model.Car;
import ru.lanit.model.Person;
import ru.lanit.model.PersonWithCars;
import ru.lanit.model.Statistics;
import ru.lanit.repository.PersonsAndCarsRepository;

@Service
@AllArgsConstructor
public class PersonsAndCarsService {

    private PersonsAndCarsRepository repository;

    public Person savePerson(Person person) {
        Person p = repository.savePerson(person);
        if (p == null) {
            throw new InvalidDataException("Person already exists");
        }
        return p;
    }

    public Car saveCar(Car car) {
        if (!car.isValidVendorAndModel()) {
            throw new InvalidDataException("Model must be in 'vendor-model' format");
        }
        Car c = repository.saveCar(car);
        if (c == null) {
            throw new InvalidDataException("Car already exists or Person does not exist or Person under 18 y.o.");
        }
        return c;
    }

    public PersonWithCars getPersonWithCarsById(long personId) {
        PersonWithCars personWithCars = repository.getPersonWithCarsById(personId);
        if (personWithCars == null) {
            throw new NotFoundException("Person with cars not found");
        }
        return personWithCars;
    }

    public Statistics getStatistics() {
        return repository.getStatistics();
    }

    public void clear() {
        repository.clear();
    }
}