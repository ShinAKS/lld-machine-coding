package com.ayush.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Lumpsum implements Comparable<Lumpsum>{

    private Integer emiNo;

    private double amount;

    @Override
    public int compareTo(Lumpsum o) {
        return this.getEmiNo().compareTo(o.getEmiNo());
    }
}
