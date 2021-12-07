package com.fk.strategy;

import com.fk.model.Rating;

import java.util.List;

public interface FilterStrategy {

    public List<Rating>getRatingsByFilter(List<Rating>givenRatings,Double value);
}
