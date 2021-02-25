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
public class Level {
    int id;
    int level_type;
    String level_name;

    public Level() {
    }

    public Level(int id, int level_type, String level_name) {
        this.id = id;
        this.level_type = level_type;
        this.level_name = level_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel_type() {
        return level_type;
    }

    public void setLevel_type(int level_type) {
        this.level_type = level_type;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }
    
    
}
