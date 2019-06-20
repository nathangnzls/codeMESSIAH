package net.netne.cmessiah.codemessiah.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Atmosphere implements JSONPopulator {
    private double pressure;
    private double humidity;
    private double visibility;

    public double getPressure() {
        return pressure;
    }
    public double getHumidity(){return humidity;}
    public double getVisibility(){return visibility;}

    @Override
    public void populate(JSONObject data) {
        pressure = data.optDouble("pressure");
        humidity = data.optDouble("humidity");
        visibility = data.optDouble("visibility");
    }

    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("pressure", pressure);
            data.put("humidity", humidity);
            data.put("visibility", visibility);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void populate(JSONArray arr) {

    }
}
