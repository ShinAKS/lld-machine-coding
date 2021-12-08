package com.ayush.mymoney.repository;

import com.ayush.mymoney.models.Fund;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository layer to store monthly records marked against indices,
 * Index is derived from month, January maps to 0, Feb maps to 1 etc.
 */
public class MonthlyPortfolioRepository {

    private static MonthlyPortfolioRepository repository = null;

    Map<Integer,List<Fund>>indexedPortfolio;

    public static MonthlyPortfolioRepository getInstance(){
        if (repository==null){
            repository = new MonthlyPortfolioRepository();
        }
        return repository;
    }

    public MonthlyPortfolioRepository(){
        this.indexedPortfolio = new HashMap<>();
    }

    public List<Fund>getFundsByIndex(int idx){
        return indexedPortfolio.get(idx);
    }

    public void addMonthlyPortfolioRecord(int monthIdx, List<Fund>monthlyFunds){
        indexedPortfolio.put(monthIdx,monthlyFunds);
    }

    public int getSize(){
        return this.indexedPortfolio.size();
    }
}
