/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hieunv;

import java.util.List;

/**
 *
 * @author vanhi
 */
public class SimilarCalculator {
    
    public double euclideanDistance(List<Double> first, List<Double> second){
        double result = 0;
        int size = Math.max(first.size(), second.size());
        for(int i = 0;i < size;i++){
            if(i > first.size()) first.add(0.0);
            if(i > second.size()) second.add(0.0);
            result = result + Math.pow(first.get(i) - second.get(i),2);
        }
        return Math.sqrt(result);
    }
}
