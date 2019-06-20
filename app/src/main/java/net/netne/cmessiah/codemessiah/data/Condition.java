package net.netne.cmessiah.codemessiah.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Condition implements JSONPopulator {
    private int code;
    private int temperature;
    private String description;
    private String date;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public String getDate(){return date;}

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");
        date = data.optString("date");
    }

    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("code", code);
            data.put("temp", temperature);
            data.put("text", description);
            data.put("date", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
    @Override
    public void populate(JSONArray arr) {

    }
}
