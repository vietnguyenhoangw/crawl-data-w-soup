/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author vietnguyenw
 */
public class Word {
    int id;
    String mean;
    String word;
    int level_type;
    int topic_id;
    String aip;
    String how_to_use;
    String example;

    public Word() {
    }

    public Word(int id, String mean, String word, int level_type, int topic_id, String aip, String how_to_use, String example) {
        this.id = id;
        this.mean = mean;
        this.word = word;
        this.level_type = level_type;
        this.topic_id = topic_id;
        this.aip = aip;
        this.how_to_use = how_to_use;
        this.example = example;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLevel_type() {
        return level_type;
    }

    public void setLevel_type(int level_type) {
        this.level_type = level_type;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getAip() {
        return aip;
    }

    public void setAip(String aip) {
        this.aip = aip;
    }

    public String getHow_to_use() {
        return how_to_use;
    }

    public void setHow_to_use(String how_to_use) {
        this.how_to_use = how_to_use;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
    
    
}
