package ru.lanit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Statistics {

    public static final Statistics STATISTICS = new Statistics();

    private Long personCount;
    private Long personWithCarsCount;
    private Long carCount;
    private Long uniqueVendorCount;

    private Statistics() {
    }
}