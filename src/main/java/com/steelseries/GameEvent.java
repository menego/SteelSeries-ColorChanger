/**
 * 11/29/2016
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: GameEvent.java
 * Version: 1.0
 */
package com.steelseries;

import com.utilities.HttpRequester;
import org.json.JSONObject;

import java.awt.*;

public class GameEvent {

    private String game;
    private String event;
    private Integer value;


    /**
     * Constructor for the class GameEvent.
     *
     * @param game
     * @param event
     * @param value
     */
    public GameEvent(String game, String event, Integer value){
        this.game = game;
        this.event = event;
        this.value = value;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * This method returns a JSON format of the current object, provided that all parameters are properly set.
     *
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = new JSONObject();
        JSONObject jsnData = new JSONObject();

        if(this.game==null||this.game.trim()==""
        ||this.event==null||this.event.trim()==""
        ||this.value==null){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        //define data Json
        jsnData.put("value", this.value);

        //define main Json
        jsnObj.put("game", this.game);
        jsnObj.put("event", this.event);
        jsnObj.put("data", jsnData);

        return jsnObj;
    }

    public void send(HttpRequester requester) throws Exception {
        requester.postRequest("/game_event", this.toJSONObject());
    }

}
