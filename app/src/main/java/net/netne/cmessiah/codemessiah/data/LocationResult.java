package net.netne.cmessiah.codemessiah.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationResult implements JSONPopulator {

    private String address;

    public String getAddress() {
        return address;
    }

    @Override
    public void populate(JSONObject data) {
        address = data.optString("formatted_address");
    }

    @Override
    public void populate(JSONArray arr) {

    }

    @Override
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();

        try {
            data.put("formatted_address", address);
        } catch (JSONException e) {}

        return data;
    }

}
