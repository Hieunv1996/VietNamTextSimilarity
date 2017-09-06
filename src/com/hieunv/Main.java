/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hieunv;

import java.util.ArrayList;
import java.util.List;
import vn.edu.vnu.uet.nlp.segmenter.UETSegmenter;

/**
 *
 * @author vanhi
 */
public class Main {
    public static void main(String[] args) {
        Sentence a = new Sentence("làm thế nào để sửa máy ủi?");
        Sentence b = new Sentence("em không cần lựa chọn thứ hai");
        List<Sentence> lstSen = new ArrayList<>();
        lstSen.add(a);
        lstSen.add(b);
        
        //Segment text using UETSegmenter+
        String modelsPath = "models"; 
	UETSegmenter segmenter = new UETSegmenter(modelsPath); // construct the segmenter
        for(Sentence sentence : lstSen){
            sentence.setSentence(segmenter.segment(sentence.getSentence()));
            System.out.println(sentence.getSentence());
        }
        
        Converter converter = new Converter(lstSen);
        List<List<Double>> vecList = converter.toWord2Vec();
        
//        for(List<Double> item : vecList){
//            System.out.println(item);
//        }
//        Set<String> features = converter.getFeatures();
//        for(String i : features) System.out.print(i+", ");
//        System.out.println("");
        
        SimilarCalculator calculator = new SimilarCalculator();
        int size = vecList.size();
        for(int i = 0;i < size-1;++i){
            for(int j = i+1;j < size;++j){
                System.out.println(i + " vs " + j + " => diff = " 
                        + calculator.euclideanDistance(vecList.get(i), vecList.get(j)));
            }
        }
    }
}
