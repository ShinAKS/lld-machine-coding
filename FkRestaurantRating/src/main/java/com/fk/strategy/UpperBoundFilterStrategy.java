package com.fk.strategy;

import com.fk.model.Rating;

import java.util.List;
import java.util.stream.Collectors;

public class UpperBoundFilterStrategy implements FilterStrategy{

    @Override
    public List<Rating> getRatingsByFilter(List<Rating> givenRatings, Double value) {
        return givenRatings.stream().filter(rating->rating.getRatingValue()<=value).collect(Collectors.toList());
    }
}
