package com.example.projectdoomsday;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class DatabaseAPIController {


    public static void sendBarcodeID(final String barcodeID, final Context context, final TextView textView, final JsonUtils jsonUtils)
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.upcitemdb.com/prod/trial/lookup?upc=" + barcodeID, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONArray jsonArray = response.getJSONArray("items");
                    JSONObject temp = (JSONObject) jsonArray.get(0);
                    String title = temp.getString("title");
                    textView.setText(title);
                    Item item = new Item(barcodeID, title);

                    jsonUtils.getItemValues().put(barcodeID, title);

                    System.out.println("BB");

                }
                catch (JSONException e)
                {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);

    }

    public static void findItemTitle(String barcodeID, Context context, final TextView textView, String itemDataFileName)
    {
        /*for(int i = 0; i < localItems.length(); i++)
        {
            JsonObject currentObject = (JsonObject) localItems.get(i);
            Gson gson = new Gson();
            Item item = gson.fromJson(currentObject, Item.class);

            if(item.getID().equals(barcodeID))
            {
                found = true;
                break;
            }
        }*/
    }


}
