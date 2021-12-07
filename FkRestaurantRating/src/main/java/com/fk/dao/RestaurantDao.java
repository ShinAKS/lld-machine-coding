package com.fk.dao;

import com.fk.exceptions.RestaurantExistsException;
import com.fk.exceptions.RestaurantNotFoundException;
import com.fk.model.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantDao {

    private static RestaurantDao restaurantDaoInstance;

    private Map<String, Restaurant>restaurantMap;

    private RestaurantDao(){
        this.restaurantMap = new HashMap<>();
    }

    public static RestaurantDao getInstance(){
        if (restaurantDaoInstance==null)restaurantDaoInstance = new RestaurantDao();
        return restaurantDaoInstance;
    }

    public void addRestaurant(Restaurant restaurant){
        if (this.restaurantMap.containsKey(restaurant.getRestaurantName())){
            throw new RestaurantExistsException();
        }
        this.restaurantMap.put(restaurant.getRestaurantName(),restaurant);
    }

    public Restaurant getRestaurant(String restaurantName){
        if (!this.restaurantMap.containsKey(restaurantName)){
            throw new RestaurantNotFoundException();
        }
        return this.restaurantMap.get(restaurantName);
    }

    public List<Restaurant> getAll(){
        return this.restaurantMap.values().stream().toList();
    }
}
