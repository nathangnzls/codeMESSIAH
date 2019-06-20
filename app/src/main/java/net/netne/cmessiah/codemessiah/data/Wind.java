package net.netne.cmessiah.codemessiah.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Wind implements JSONPopulator {
    private int speed;

    public int getSpeed() {
        return speed;
    }

    @Override
    public void populate(JSONObject data) {
        speed = data.optInt("speed");
    }

    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("speed", speed);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
    @Override
    public void populate(JSONArray arr) {

    }
}
