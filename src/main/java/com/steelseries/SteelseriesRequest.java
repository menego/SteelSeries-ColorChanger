/**
 * 1/29/2017
 * Author: Nicola Meneghetti
 * Project: SteelSeries-ColorChanger
 * File: SteelseriesRequest.java
 * Version: 1.0
 */
package com.steelseries;

import com.utilities.HttpRequester;
import org.json.JSONObject;

public interface SteelseriesRequest {

    JSONObject toJSONObject() throws Exception;

    void send(HttpRequester requester) throws Exception;

}
