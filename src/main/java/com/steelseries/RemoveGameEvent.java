/**
 * 11/29/2016
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: RemoveGameEvent.java
 * Version: 1.0
 */
package com.steelseries;

import com.utilities.HttpRequester;
import org.json.JSONObject;

public class RemoveGameEvent implements SteelseriesRequest {

    private String game;
    private String event;


    /**
     * Constructor for the RemoveGameEvent object.
     * The value for {@code game} and {@code event} are limited to uppercase A-Z, 0-9, hyphen, and underscore characters.<br/>
     *
     * @param game
     * @param event
     */
    public RemoveGameEvent(String game, String event){
        this.game = game;
        this.event = event;
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

    /**
     * This method returns a JSON format of the current object, provided that all parameters are properly set.
     *
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = new JSONObject();
        if(this.game==null||this.game.trim().equals("")
            ||this.event==null||this.event.trim().equals("")){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        jsnObj.put("game", this.game);
        jsnObj.put("event", this.event);

        return jsnObj;
    }

    public void send(HttpRequester requester) throws Exception {
        requester.postRequest("/remove_game_event", this.toJSONObject());
    }

}
