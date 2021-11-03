package ru.lanit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PersonWithCars extends Person {

    @NotNull(message = "cars must not be null")
    private List<Car> cars;

    public PersonWithCars(@NotNull(message = "id must not be null") Long id,
                          @NotBlank(message = "name must not be null") String name,
                          @NotNull(message = "birthdate must not be null") @Past(message = "birthdate must be in the past") Date birthdate,
                          List<Car> cars) {
        super(id, name, birthdate);
        this.cars = cars;
    }
}