/**
 * 11/26/2016
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: RegisterGame.java
 * Version: 1.0
 */
package com.steelseries;

import org.json.JSONObject;

public class RegisterGame {

    private String id;
    private String name;
    private Integer color;


    /**
     * Constructor for the RegtisterGame object.
     * The value for {@code id} is limited to uppercase A-Z, 0-9, hyphen, and underscore characters.<br/>
     * The value for {@code name} are limited to uppercase A-Z, lowercase a-z, 0-9, hyphen, and underscore characters.<br/>
     * The {@code color} is an integer value between 0 and 11 see
     * <a href="https://github.com/SteelSeries/gamesense-sdk/blob/master/doc/api/writing-handlers-in-json.md#default-icon-colors">here</a>.
     * <br/>
     * 0:  Orange <br/>
     * 1:  Gold <br/>
     * 2:  Yellow <br/>
     * 3:  Green <br/>
     * 4:  Teal <br/>
     * 5:  Light blue <br/>
     * 6:  Blue <br/>
     * 7:  Purple <br/>
     * 8:  Fuschia <br/>
     * 9:  Pink <br/>
     * 10: Red <br/>
     * 11: Silver <br/>
     *
     * @param id
     * @param name
     * @param color
     */
    public RegisterGame(String id, String name, Integer color){
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    /**
     * This method returns a JSON format of the current object, provided that all parameters are properly set.
     *
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = new JSONObject();
        if(this.id==null||this.id.trim()==""
            ||this.name==null||this.name.trim()==""
            ||this.color==null||this.color<0||this.color>11){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        jsnObj.put("game", this.id);
        jsnObj.put("game_display_name", this.name);
        jsnObj.put("icon_color_id", this.color);

        return jsnObj;
    }

}
