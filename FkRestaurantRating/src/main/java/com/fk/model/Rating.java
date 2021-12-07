package com.fk.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
public class Rating {

    private Double ratingValue;

    private Optional<List<String>>items;

    private Optional<String>comment;

    public Rating(@NonNull Double rating,Optional<List<String>>items,Optional<String> comment){
        this.ratingValue = rating;
        this.items = items;
        this.comment = comment;
    }
}
