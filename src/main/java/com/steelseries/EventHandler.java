/**
 * 1/26/2017
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: EventHandler.java
 * Version: 1.0
 */
package com.steelseries;

import org.json.JSONObject;

public abstract class EventHandler {

    private String device_type;
    private String mode;
    private String zone;

    /**
     * Constructor for the class EventHandler
     * @param device_type
     * @param mode
     * @param zone
     */
    public EventHandler(String device_type, String mode, String zone){
        this.device_type = device_type;
        this.mode = mode;
        this.zone = zone;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }


    /**
     * This method returns a JSON format of the current object, provided that all parameters are properly set.
     *
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsnObj = new JSONObject();

        if(this.device_type ==null||this.mode==null||this.zone==null
        || this.device_type.trim().equals("")||this.mode.trim().equals("")||this.zone.trim().equals("")){

            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        jsnObj.put("device-type", this.device_type);
        jsnObj.put("mode", this.mode);
        jsnObj.put("zone", this.zone);

        return jsnObj;
    }

}
