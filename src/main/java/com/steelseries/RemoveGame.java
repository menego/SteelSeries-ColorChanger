/**
 * 11/26/2016
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: RemoveGame.java
 * Version: 1.0
 */
package com.steelseries;

import com.utilities.HttpRequester;
import org.json.JSONObject;

public class RemoveGame implements SteelseriesRequest {

    private String game;


    /**
     * Constructor for the RemoveGame object.
     * The value for {@code game} and {@code event} are limited to uppercase A-Z, 0-9, hyphen, and underscore characters.<br/>
     *
     * @param game
     */
    public RemoveGame(String game){
        this.game = game;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    /**
     * This method returns a JSON format of the current object, provided that all parameters are properly set.
     *
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = new JSONObject();
        if(this.game==null||this.game.trim().equals("")){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        jsnObj.put("game", this.game);

        return jsnObj;
    }

    public void send(HttpRequester requester) throws Exception {
        requester.postRequest("/remove_game", this.toJSONObject());
    }

}
