package net.netne.cmessiah.codemessiah.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Units implements JSONPopulator {
    private String temperature;
    private String speed;
    private String pressure;

    public String getTemperature() {
        return temperature;
    }
    public String getSpeed(){return speed;}
    public String getPressure(){return pressure;}

    @Override
    public void populate(JSONObject data) {

        temperature = data.optString("temperature");
        speed = data.optString("speed");
        pressure = data.optString("pressure");
    }


    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("temperature", temperature);
            data.put("speed", speed);
            data.put("pressure",pressure);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
    @Override
    public void populate(JSONArray arr) {

    }
}
