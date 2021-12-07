package com.fk.strategy;

import com.fk.model.Rating;

import java.util.List;
import java.util.stream.Collectors;

public class LowerBoundFilterStrategy implements FilterStrategy{
    @Override
    public List<Rating> getRatingsByFilter(List<Rating> givenRatings,Double bound) {
        return givenRatings
                .stream()
                .filter(rating->rating.getRatingValue()>=bound)
                .collect(Collectors.toList());
    }
}
