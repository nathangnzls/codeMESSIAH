package net.netne.cmessiah.codemessiah.data;

import org.json.JSONArray;
import org.json.JSONObject;


public interface JSONPopulator {
    void populate(JSONObject data);

    void populate(JSONArray arr);

    JSONObject toJSON();



}
