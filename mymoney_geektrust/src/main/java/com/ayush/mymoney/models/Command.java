package com.ayush.mymoney.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
/**
 * Commands arguments will contain
 * name : name of the command (ALLOCATE, SIP, CHANGE)
 * params : Value of params, can be values of the fund, can be month etc.
 */
public class Command {
    String name;
    List<String> params;
}
