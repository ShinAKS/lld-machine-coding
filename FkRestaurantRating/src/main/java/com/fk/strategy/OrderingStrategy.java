package com.fk.strategy;

import com.fk.model.Rating;

import java.util.List;

public interface OrderingStrategy {

    List<Rating>getOrderedRatings(List<Rating>ratings);
}
