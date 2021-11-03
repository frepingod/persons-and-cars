package ru.lanit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
public class Person {

    @NotNull(message = "id must not be null")
    private Long id;

    @NotBlank(message = "name must not be null")
    private String name;

    @NotNull(message = "birthdate must not be null")
    @Past(message = "birthdate must be in the past")
    private Date birthdate;

    @JsonIgnore
    public boolean isAdult() {
        return howOld() >= 18;
    }

    private int howOld() {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(this.birthdate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(new Date());
        return endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
    }
}