package com.fk.strategy;

import com.fk.model.Rating;

import java.util.List;
import java.util.stream.Collectors;

public class AscendingOrderStrategy implements OrderingStrategy{
    @Override
    public List<Rating> getOrderedRatings(List<Rating> ratings) {
        return ratings.stream()
                .sorted((r1,r2)->r1.getRatingValue().compareTo(r2.getRatingValue()))
                .collect(Collectors.toList());
    }
}
