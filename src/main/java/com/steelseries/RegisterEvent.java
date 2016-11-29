/**
 * 11/29/2016
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: RegisterEvent.java
 * Version: 1.0
 */
package com.steelseries;

import com.utilities.HttpRequester;
import org.json.JSONObject;

public class RegisterEvent {

    private String game;
    private String event;
    private Integer icon;


    /**
     * Constructor for the RegisterEvent object.
     * The value for {@code game} and {@code event} are limited to uppercase A-Z, 0-9, hyphen, and underscore characters.<br/>
     * The {@code icon} is an integer value between 0 and 17 see
     * <a href="https://github.com/SteelSeries/gamesense-sdk/blob/master/doc/api/event-icons.md">here</a>.
     *
     * @param game
     * @param event
     * @param icon
     */
    public RegisterEvent(String game, String event, Integer icon){
        this.game = game;
        this.event = event;
        this.icon = icon;
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

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    /**
     * This method returns a JSON format of the current object, provided that all parameters are properly set.
     *
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = new JSONObject();
        if(this.game==null||this.game.trim()==""
            ||this.event==null||this.event.trim()==""
            ||this.icon==null||this.icon<0||this.icon>17){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        jsnObj.put("game", this.game);
        jsnObj.put("event", this.event);
        jsnObj.put("icon", this.icon);

        return jsnObj;
    }

    public void send(HttpRequester requester) throws Exception {
        requester.postRequest("/register_game_event", this.toJSONObject());
    }

}
