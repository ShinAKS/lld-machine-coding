Restaurant Rating Platform

Problem Statement

Your aim is to implement a restaurant rating system. Following are the features that it should
have:

● Register: Users should be able to register to give ratings for restaurants.

● Rate: Among the pre-populated list of restaurants, registered users can give rating
between 1 to 10. If the same user gives a new rating for the same restaurant, it should
be overwritten.

● Rate with options: Along with a rating, users can add optional dish name(s) and rating
description.

● List reviews: When fetching reviews for a restaurant based on filter and order by

    ○ Filter: Ratings should be able to be filtered based on range of rating (eg. ratings
        between 4 (lower range) and 7 (higher range))

        ■ Lower range value

        ■ Higher range value
    ○ Order By: Order by
        ■ Ascending order of rating

        ■ Descending order of rating

        Default should be with a higher rating first without any filter.

● Describe: When describing a restaurant, along with the name, give the average rating it
received so far.

● List: List restaurants based on descending order of (average) rating.
Bonus

● Upgrade: For each user a level will be defined which determines their foodiness. A new
user by default will have level 1. The level would be allotted based on the number of
ratings given by a user

    ○ 4 ratings: level 2

    ○ 8 ratings: level 3

    ○ 16 ratings: level 4

● List reviews:

    ○ Filter: Add range of levels of reviewers whose ratings you want to fetch

    ○ Order by: Order reviews by levels of reviewers

Requirements
1. Add restaurants: add_restaurant(“<restaurant>”)
2. Add users: add_user(“<username>”)
3. Add rating: add_rating(“<username>”, “<restaurant>”, <rating>)
4. Add rating with details: add_rating(“<username>”, “<restaurant>”, <rating>, [<dish
names>], “<review details>”)
5. List top n ratings for a restaurant: list_ratings(“<restaurant>”, n)
6. List top n ratings for a restaurant based on a filter and order by:
list_ratings(“<restaurant>”, n, <filters>, <orderBy>)
7. Describe restaurant with average rating: get_restaurant(“<restaurant>”) → {<restaurant>,
<avg_rating>}
8. List top n restaurants based on ratings: list_restaurants(n) → [restaurant1, restaurant2]
Here restaurant1 has more average rating than restaurant2
Sample Test Cases (You may change input/output format without change in functionality)
1. Add restaurants
add_restaurant(“restaurant1”)
add_restaurant(“restaurant2”)
add_restaurant(“restaurant3”)
2. Add user
add_user(“user1”)
add_user(“user2”)
3. Add rating
add_rating(“user1”, “restaurant1”, 5)
add_rating(“user1”, “restaurant2”, 8)
add_rating(“user2”, “restaurant1”, 7)
add_rating(“user1”, “restaurant3”, 8)
4. Add rating with details
add_rating(“user1”, “restaurant2”, 7, [“samosa”, “tea”], “Samosa
and tea were good”)
add_rating(“user2”, “restaurant1”, 8, [“dosa”], “Dosa was
awesome”)
5. List ratings
list_ratings(“restaurant1”, 2)
Output:
Rating: 8, Items: “Dosa”, Review: “Dosa was awesome”
Rating: 7, Items: “”, Review: “”
Rating: 5, Items: “”, Review: “”
6. List ratings with filter
list_ratings(“restaurant1”, 2, filter: {lowerLimit: 8}, orderBy:
asc)
Output:
Rating: 8, Items: “Dosa”, Review: “Dosa was awesome”
7. Get restaurant
get_restaurant(“restaurant1”)
Output:
Name: “restaurant1”, Rating: 6.66
8. List top n restaurants
list_restaurants(3)
Output:
Name: “restaurant3”, Rating: 8
Name: “restaurant2”, Rating: 7.5
Name: “restaurant1”, Rating: 6.66
Expectations
1. Create sample data yourself. You can put it in a file, test case or main driver program.
2. Code should be demo-able, either using test cases or main driver function, and cover the
features.
3. It should be extensible, should easily accommodate new requirements with minimal
change.
4. Code should handle edge cases properly and fail gracefully.
5. Don’t use any database, use in-memory data structure.
