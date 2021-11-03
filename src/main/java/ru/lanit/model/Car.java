package ru.lanit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Car {

    @NotNull(message = "id must not be null")
    private Long id;

    @NotBlank(message = "model must not be null")
    private String model;

    @NotNull(message = "horsepower must not be null")
    @Min(1)
    private Integer horsepower;

    @NotNull(message = "ownerId must not be null")
    private Long ownerId;

    @JsonIgnore
    public boolean isValidVendorAndModel() {
        String[] parts = getModelAndVendor();
        return parts.length == 2 && !parts[0].contains("-");
    }

    @JsonIgnore
    public String getVendor() {
        return getModelAndVendor()[0].toLowerCase();
    }

    @JsonIgnore
    public String getModelWithoutVendor() {
        return getModelAndVendor()[1].toLowerCase();
    }

    private String[] getModelAndVendor() {
        return this.model.split("-", 2);
    }
}