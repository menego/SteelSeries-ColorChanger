/**
 * 11/29/2016
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: BindEvent.java
 * Version: 1.0
 */
package com.steelseries;

import com.utilities.HttpRequester;
import org.json.JSONObject;

public class BindEvent implements SteelseriesRequest {

    private String game;
    private String event;
    private Integer minValue;
    private Integer maxValue;
    private Integer icon;
    private JSONObject[] handlers;


    /**
     * Constructor for the BindEvent object.
     * The value for {@code game} and {@code event} are limited to uppercase A-Z, 0-9, hyphen, and underscore characters.<br/>
     * The {@code icon} is an integer value between 0 and 17 see
     * <a href="https://github.com/SteelSeries/gamesense-sdk/blob/master/doc/api/event-icons.md">here</a>.
     *
     * @param game
     * @param event
     * @param minValue
     * @param maxValue
     * @param icon
     * @param handlers
     */
    public BindEvent(String game, String event,  Integer minValue, Integer maxValue, Integer icon, JSONObject[] handlers){
        this.game = game;
        this.event = event;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.icon = icon;
        this.handlers = handlers;
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

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public JSONObject[] getHandlers() {
        return handlers;
    }

    public void setHandlers(JSONObject[] handlers) {
        this.handlers = handlers;
    }

    /**
     * This method returns a JSON format of the current object, provided that all parameters are properly set.
     *
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = new JSONObject();
        if(this.game==null||this.game.trim().equals("")
            ||this.event==null||this.event.trim().equals("")
            ||this.minValue==null||this.maxValue==null
            ||this.icon==null||this.icon<0||this.icon>17
            ||this.handlers==null||this.handlers.length==0){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        jsnObj.put("game", this.game);
        jsnObj.put("event", this.event);
        jsnObj.put("min_value", this.minValue);
        jsnObj.put("max_value", this.maxValue);
        jsnObj.put("icon", this.icon);
        jsnObj.put("handlers", this.handlers);

        return jsnObj;
    }

    public void send(HttpRequester requester) throws Exception {
        requester.postRequest("/bind_game_event", this.toJSONObject());
    }

}
