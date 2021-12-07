package com.fk.service;

import com.fk.dao.RestaurantDao;
import com.fk.exceptions.RestaurantNotFoundException;
import com.fk.model.Rating;
import com.fk.model.Restaurant;
import com.fk.model.User;
import com.fk.strategy.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestaurantService implements IRestaurantService{

    private UserService userService;

    private RestaurantDao restaurantDao;

    public RestaurantService(UserService userService){
        this.userService = userService;
        this.restaurantDao = RestaurantDao.getInstance();
    }

    @Override
    public void addRestaurant(String resName) {
        restaurantDao.addRestaurant(new Restaurant(resName));
    }

    @Override
    public void addRating(String user, String restaurant, Double rating, Optional<List<String>> items, Optional<String> comment) {


        try{
            User foundUser = null;
            foundUser = userService.getUser(user);
            Restaurant foundRestaurant = restaurantDao.getRestaurant(restaurant);
            foundRestaurant.getRatings().put(foundUser,new Rating(rating,items,comment));
            modifyRestaurantRating(foundRestaurant);
        }catch(Exception e){
            System.out.println("User or restaurant not found");
            return;
        }


    }

    @Override
    public void listRatings(String restaurantName, Integer n, Optional<Map<String, Double>> filters, Optional<String> order) {
        try {
            Restaurant restaurant = restaurantDao.getRestaurant(restaurantName);
            List<Rating>restaurantRatings = restaurant.getRatings().values().stream().toList();
            if (filters.isPresent()){

                if (filters.get().containsKey("lowerBound")){
                    FilterStrategy strategy = new LowerBoundFilterStrategy();
                    restaurantRatings = strategy.getRatingsByFilter(restaurantRatings,filters.get().get("lowerBound"));
                }

                if (filters.get().containsKey("upperBound")){
                    FilterStrategy strategy = new UpperBoundFilterStrategy();
                    restaurantRatings = strategy.getRatingsByFilter(restaurantRatings,filters.get().get("upperBound"));
                }
            }
            OrderingStrategy orderingStrategy = new DescendingOrderStrategy();
            if (order.isPresent() && order.get().equals("ascending")){
                orderingStrategy = new AscendingOrderStrategy();
            }
            restaurantRatings = orderingStrategy.getOrderedRatings(restaurantRatings);

            for (int i = 0 ; i<Math.min(restaurantRatings.size(),n) ; i++){
                Rating rating = restaurantRatings.get(i);
                System.out.println(rating);
            }
//            restaurantRatings.stream().forEach(rating->System.out.println(rating));
        }catch (RestaurantNotFoundException e){
            System.out.println("Restaurant was not found");
        }
    }

    @Override
    public void getRating(Restaurant restaurant) {
        System.out.println(restaurant.getRestaurantName() + " " + restaurant.getResRating());
    }

    @Override
    public void getTopN(Integer n) {
        List<Restaurant>restaurantsList = restaurantDao.getAll();
        restaurantsList = restaurantsList.stream().sorted((r1,r2)->r2.getResRating().compareTo(r1.getResRating())).collect(Collectors.toList());
        for (int i = 0 ; i<Math.min(n,restaurantsList.size()) ; i++){
            System.out.println(restaurantsList.get(i).getRestaurantName() + " " + restaurantsList.get(i).getResRating());
        }
//        restaurantsList.stream().forEach(restaurant -> System.out.println(restaurant.getRestaurantName() + " " + restaurant.getResRating()));
    }

    private void modifyRestaurantRating(Restaurant res){
        res.updateRating();
    }

    public void getRestaurant(String name){

        try{
            Restaurant res = this.restaurantDao.getRestaurant(name);
            System.out.println(res.getRestaurantName() + " --> " + res.getResRating());
        }
        catch (RestaurantNotFoundException e){
            System.out.println("Restaurant not found");
        }
    }


}
