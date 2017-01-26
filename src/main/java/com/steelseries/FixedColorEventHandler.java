/**
 * 1/26/2017
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: FixedColorEventHandler.java
 * Version: 1.0
 */
package com.steelseries;

import org.json.JSONObject;

public class FixedColorEventHandler extends EventHandler {

    private JSONObject[] colorSet;

    /**
     * Constructor for the class EventHandler
     *
     * @param device_type
     * @param mode
     * @param zone
     * @param colorSet
     */
    public FixedColorEventHandler(String device_type, String mode, String zone, JSONObject[] colorSet) {
        super(device_type, mode, zone);
        this.colorSet = colorSet;
    }

    public JSONObject[] getColorSet() {
        return colorSet;
    }

    public void setColorSet(JSONObject[] colorSet) {
        this.colorSet = colorSet;
    }

    /**
     * This method returns a JSON format of the current object, provided that all parameters are properly set.
     * This is an override of the abstract superclass EventHandler.
     *
     * @return JSONObject
     * @throws Exception
     */
    @Override
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = super.toJSONObject();

        if(this.colorSet==null||this.colorSet.length == 0){

            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        jsnObj.put("color", this.colorSet);

        return jsnObj;
    }
}
