package com.fk.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Restaurant {

    private String restaurantName;

    Map<User,Rating> ratings;

    private Double resRating;

    public Restaurant(String name){
        this.restaurantName = name;
        this.ratings = new HashMap<>();
        this.resRating = 0.0;
    }

    public void updateRating(){
        List<Rating>ratings = this.getRatings().values().stream().toList();
        Double totalValue = 0.0;
        for (int i = 0 ; i<ratings.size() ; i++){
            totalValue+=ratings.get(i).getRatingValue();
        }
        this.resRating = totalValue/ratings.size();

    }
}
