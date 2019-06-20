package net.netne.cmessiah.codemessiah.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Item implements JSONPopulator {
    private Condition condition;
    private String description;
    private String forecast;


    public String getForecast(){return forecast;}
    public Condition getCondition() {
        return condition;
    }
    public String getDescription(){return description;}


    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
        forecast = data.optString("forecast");
        description = data.optString("description");

    }

    @Override
    public void populate(JSONArray arr){
    }

    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();
        try {
            data.put("condition", condition.toJSON());
            data.put("description", description);
            data.put("forecast", forecast);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

