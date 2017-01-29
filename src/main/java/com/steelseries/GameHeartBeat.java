package com.steelseries;

import com.utilities.HttpRequester;
import org.json.JSONObject;

import java.util.TimerTask;

/**
 * 1/29/2017
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: HeartBeat.java
 * Version: 1.0
 */
public class GameHeartBeat extends TimerTask implements SteelseriesRequest{

    private String game;
    private HttpRequester requester;

    public GameHeartBeat(String game, HttpRequester requester){
        this.game = game;
        this.requester = requester;
    }

    public void run() {
        try {
            this.send(this.requester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = new JSONObject();

        if(this.game==null||this.game.trim().equals("")){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        //define main Json
        jsnObj.put("game", this.game);

        return jsnObj;
    }

    public void send(HttpRequester requester) throws Exception {
        requester.postRequest("/game_heartbeat", this.toJSONObject());
    }
}