package ru.lanit.repository;

import ru.lanit.model.Car;
import ru.lanit.model.Person;
import ru.lanit.model.PersonWithCars;
import ru.lanit.model.Statistics;

public interface PersonsAndCarsRepository {

    Person savePerson(Person person);

    Car saveCar(Car car);

    PersonWithCars getPersonWithCarsById(long personId);

    Statistics getStatistics();

    void clear();
}