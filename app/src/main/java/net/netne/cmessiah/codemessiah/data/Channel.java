package net.netne.cmessiah.codemessiah.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Channel implements JSONPopulator {
    private Units units;
    private Item item;
    private String location;
    private Wind wind;
    private Atmosphere atmosphere;
    private Astronomy astronomy;

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    public String getLocation() {
        return  location;
    }

    public Wind getWind(){return wind;}

    public Atmosphere getAtmosphere(){return atmosphere;}

    public Astronomy getAstronomy(){return astronomy;}


    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));

        atmosphere = new Atmosphere();
        atmosphere.populate(data.optJSONObject("atmosphere"));

        astronomy = new Astronomy();
        astronomy.populate(data.optJSONObject("astronomy"));


        JSONObject locationData = data.optJSONObject("location");

        String region = locationData.optString("region");
        String country = locationData.optString("country");

        location = String.format("%s, %s", locationData.optString("city"), (region.length() != 0 ? region : country));
    }

    @Override
    public JSONObject toJSON() {

        JSONObject data = new JSONObject();

        try {
            data.put("units", units.toJSON());
            data.put("item", item.toJSON());
            data.put("wind", wind.toJSON());
            data.put("atmosphere", atmosphere.toJSON());
            data.put("astronomy", astronomy.toJSON());
            data.put("requestLocation", location);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void populate(JSONArray arr) {

    }

}
