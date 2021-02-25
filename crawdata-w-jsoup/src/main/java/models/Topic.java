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
public class Topic {
    int id;
    String topic_name_vi;
    String topic_name_eng;

    public Topic() {
    }

    public Topic(int id, String topic_name_vi, String topic_name_eng) {
        this.id = id;
        this.topic_name_vi = topic_name_vi;
        this.topic_name_eng = topic_name_eng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic_name_vi() {
        return topic_name_vi;
    }

    public void setTopic_name_vi(String topic_name_vi) {
        this.topic_name_vi = topic_name_vi;
    }

    public String getTopic_name_eng() {
        return topic_name_eng;
    }

    public void setTopic_name_eng(String topic_name_eng) {
        this.topic_name_eng = topic_name_eng;
    }
    
    
}
