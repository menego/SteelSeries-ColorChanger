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
    private String deviceType;
    private String zone;
    private java.awt.Color color;
    private String mode;


    /**
     * Constructor for the class GameEvent.
     *
     * @param game
     * @param event
     * @param deviceType
     * @param zone
     * @param color
     * @param mode
     */
    public GameEvent(String game, String event, String deviceType, String zone, java.awt.Color color, String mode){
        this.game = game;
        this.event = event;
        this.deviceType = deviceType;
        this.zone = zone;
        this.color = color;
        this.mode = mode;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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
        JSONObject jsnValue = new JSONObject();
        JSONObject jsnColor = new JSONObject();

        if(this.game==null||this.game.trim()==""
        ||this.event==null||this.event.trim()==""
        ||this.deviceType==null||this.deviceType.trim()==""
        ||this.zone==null||this.zone.trim()==""
        ||this.color==null
        ||this.mode==null||this.mode.trim()==""){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        //define color Json
        jsnColor.put("red", this.color.getRed());
        jsnColor.put("green", this.color.getGreen());
        jsnColor.put("blue", this.color.getBlue());

        //define value Json
        jsnValue.put("device-type", this.deviceType);
        jsnValue.put("zone", this.zone);
        jsnValue.put("color", jsnColor);
        jsnValue.put("mode", this.mode);

        //define data Json
        jsnData.put("value", jsnValue);

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
