/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hieunv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author vanhi
 */
public class Converter {

    private List<Sentence> sentences;
    private Set<String> stopwordList = new HashSet<>();
    private static String url = "./vnstopword.txt";
    private Set<String> features = new HashSet<>();

    public Set<String> getFeatures() {
        splitText();
        return features;
    }

    public Converter(List<Sentence> sentences) {
        this.sentences = sentences;
        readStopWords();
    }

    private void readStopWords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                stopwordList.add(line.toLowerCase().trim());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<List<String>> splitText() {
        List<List<String>> rs = new ArrayList<>();
        for (Sentence sentence : sentences) {
            List<String> item = new ArrayList<>();
            String[] a = sentence.getSentence().toLowerCase().split("[^_a-záàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]+");
            for (String i : a) {
                if (!stopwordList.contains(i)) {
                    item.add(i);
                    features.add(i);
                }
            }
            rs.add(item);
        }
        return rs;
    }

    public List<List<Double>> toWord2Vec() {
        List<List<Double>> rs = new ArrayList<>();

        List<List<String>> documents = splitText();

        TFIDFCalculator calculator = new TFIDFCalculator();
        for (List<String> docs : documents) {
            List<Double> item = new ArrayList<>();
            for (String word : features) {
                item.add(calculator.tfIdf(docs, documents, word));
            }
            rs.add(item);
        }
        return rs;
    }
}
