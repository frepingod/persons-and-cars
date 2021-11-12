package ru.lanit.repository;

import org.springframework.stereotype.Repository;
import ru.lanit.model.Car;
import ru.lanit.model.Person;
import ru.lanit.model.PersonWithCars;
import ru.lanit.model.Statistics;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PersonsAndCarsRepositoryImpl implements PersonsAndCarsRepository {

    private final Map<Long, Person> persons = new ConcurrentHashMap<>();
    private final Map<Long, PersonWithCars> personsWithCars = new ConcurrentHashMap<>();
    private final Map<Long, Car> cars = new ConcurrentHashMap<>();
    private final Set<String> uniqueVendor = new HashSet<>();

    @Override
    public Person savePerson(Person person) {
        long id = person.getId();
        if (!persons.containsKey(id) || !personsWithCars.containsKey(id)) {
            persons.put(id, person);
            return person;
        }
        return null;
    }

    @Override
    public Car saveCar(Car car) {
        long carId = car.getId();
        if (!cars.containsKey(carId)) {
            long ownerId = car.getOwnerId();
            if (personsWithCars.containsKey(ownerId)) {
                personsWithCars.get(ownerId).getCars().add(car);
                cars.put(carId, car);
                uniqueVendor.add(car.getVendor());
                return car;
            } else {
                Person owner = persons.get(ownerId);
                if (owner != null && owner.isAdult()) {
                    persons.remove(ownerId);
                    personsWithCars.put(ownerId,
                            new PersonWithCars(
                                    owner.getId(),
                                    owner.getName(),
                                    owner.getBirthdate(),
                                    new ArrayList<>(Collections.singletonList(car))
                            )
                    );
                    cars.put(carId, car);
                    uniqueVendor.add(car.getVendor());
                    return car;
                }
            }
        }
        return null;
    }

    @Override
    public PersonWithCars getPersonWithCarsById(long personId) {
        return personsWithCars.get(personId);
    }

    @Override
    public Statistics getStatistics() {
        return new Statistics(
                (long) persons.size(),
                (long) personsWithCars.size(),
                (long) cars.size(),
                (long) uniqueVendor.size()
        );
    }

    @Override
    public void clear() {
        persons.clear();
        personsWithCars.clear();
        cars.clear();
        uniqueVendor.clear();
    }
}