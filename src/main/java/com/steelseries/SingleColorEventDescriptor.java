/**
 * 1/26/2017
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: SingleColorEventDescriptor.java
 * Version: 1.0
 */
package com.steelseries;

import org.json.JSONObject;

import java.awt.*;

public class SingleColorEventDescriptor {

    private Integer low;
    private Integer high;
    private java.awt.Color color;

    /**
     * Constructor for the SingleColorEventDescriptor object.
     * @param low
     * @param high
     * @param color
     */
    public SingleColorEventDescriptor(Integer low, Integer high, java.awt.Color color){
        this.low = low;
        this.high = high;
        this.color = color;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer min) {
        this.low = min;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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
        JSONObject jsnColor = new JSONObject();

        if(this.low ==null||this.high==null||this.color==null){
            throw new Exception(this.getClass() + ": not all parameters are set.");
        }

        //define color Json
        jsnColor.put("red", this.color.getRed());
        jsnColor.put("green", this.color.getGreen());
        jsnColor.put("blue", this.color.getBlue());

        jsnObj.put("low", this.low);
        jsnObj.put("high", this.high);
        jsnObj.put("color", jsnColor);

        return jsnObj;
    }

}
