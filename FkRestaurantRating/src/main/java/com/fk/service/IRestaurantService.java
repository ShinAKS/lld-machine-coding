package com.fk.service;

import com.fk.dao.RestaurantDao;
import com.fk.model.Restaurant;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRestaurantService {

    void addRestaurant(String resName);

    void addRating(String user, String restaurant, Double rating, Optional<List<String>> items, Optional<String> comment);

    void listRatings(String restaurantName, Integer n, Optional<Map<String,Double>>filters,Optional<String>order);

    void getRating(Restaurant restaurant);

    void getTopN(Integer n);

    void getRestaurant(String name);

}
