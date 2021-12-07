package com.fk.strategy;

import com.fk.model.Rating;

import java.util.List;
import java.util.stream.Collectors;

public class DescendingOrderStrategy implements OrderingStrategy{
    @Override
    public List<Rating> getOrderedRatings(List<Rating> ratings) {
        return ratings.stream()
                .sorted((r1,r2)->r2.getRatingValue().compareTo(r1.getRatingValue()))
                .collect(Collectors.toList());
    }
}
