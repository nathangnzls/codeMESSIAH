package net.netne.cmessiah.codemessiah.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nathan on 8/10/2016.
 */
public class Astronomy implements JSONPopulator {
    private String sunrise;
    private String sunset;


    public String getSunrise() {
        return sunrise;
    }

    public String getSunset(){
        return sunset;
    }


    @Override
    public void populate(JSONObject data) {

        sunrise = data.optString("sunrise");
        sunset = data.optString("sunset");

    }


    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("sunrise", sunrise);
            data.put("sunset", sunset);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void populate(JSONArray arr) {
       return;
    }
}
