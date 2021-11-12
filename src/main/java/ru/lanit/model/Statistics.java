package ru.lanit.model;

import lombok.*;

@Data
@AllArgsConstructor
public class Statistics {

    private Long personCount;
    private Long personWithCarsCount;
    private Long carCount;
    private Long uniqueVendorCount;
}