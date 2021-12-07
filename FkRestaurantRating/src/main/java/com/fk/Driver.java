package com.fk;

import com.fk.model.Restaurant;
import com.fk.service.RestaurantService;
import com.fk.service.UserService;

import java.util.*;

public class Driver {

    public static void main(String[] args){

        UserService userService = new UserService();

        RestaurantService restaurantService = new RestaurantService(userService);

        userService.addUser("user1");
        userService.addUser("user2");
        userService.addUser("user3");
        userService.addUser("user4");
        userService.addUser("user5");
//        userService.addUser("user2");

        restaurantService.addRestaurant("restaurant1");
        restaurantService.addRestaurant("restaurant2");
        restaurantService.addRestaurant("restaurant3");

        restaurantService.addRating("user1","restaurant1",5.0, Optional.empty(),Optional.empty());
        restaurantService.addRating("user1","restaurant2",8.0, Optional.empty(),Optional.empty());

        restaurantService.addRating("user2","restaurant2",7.0, Optional.empty(),Optional.empty());
        restaurantService.addRating("user1","restaurant3",8.0, Optional.empty(),Optional.empty());
        restaurantService.addRating("abc","restaurant3",5.0, Optional.empty(),Optional.empty());
        restaurantService.addRating("user3","restaurant3",9.0, Optional.empty(),Optional.empty());
        restaurantService.addRating("user4","restaurant3",6.0, Optional.empty(),Optional.empty());
        restaurantService.addRating("user5","restaurant3",2.0, Optional.empty(),Optional.empty());

        Map<String,Double> mp = new HashMap<>();

        mp.put("lowerBound",4.0);
        mp.put("upperBound",9.0);

        restaurantService.listRatings("restaurant3",2,Optional.of(mp),Optional.of("ascending"));

        restaurantService.getRestaurant("restaurant3");

        restaurantService.getTopN(2);

        List<String> items = Arrays.asList("Samosa","Tea");
        String complement1 = "Samosa and tea were good";
        List<String> items2 = Arrays.asList("Dosa");
        String complement2 = "Dosa was good";

        restaurantService.addRating("user2","restaurant1",6.0, Optional.of(items),Optional.of(complement1));

        restaurantService.listRatings("restaurant1",5,Optional.empty(),Optional.empty());

        restaurantService.getRestaurant("restaurant2");
//
//        //negative case, res doesn't exist
//        restaurantService.getRestaurant("abcd");

    }
}
