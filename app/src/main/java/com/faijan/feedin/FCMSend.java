package com.faijan.feedin;

import android.content.Context;
import android.os.StrictMode;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FCMSend {
    private static final String BASE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "key=AAAAim7znO4:APA91bGQE0DTs65SaebowdJbUVwuk9VLK6l3Lt1RGdNVgBQqlVZvPbbh469ji1ZWw0JWd-bZaHdPa4nLPZzhsakI3Y2bK2J0sNCw3l4QxDjx-fm33QwTZhSJ-2RtB7c7EvKvgZXzQlbC";

    public static void  pushNotification(Context context, String token, String title, String messege){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(context);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("to", token);
            JSONObject notificaion = new JSONObject();
            notificaion.put("title", title);
            notificaion.put("body", messege);
            jsonObject.put("notification", notificaion);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("FCM "+ response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", SERVER_KEY);
                    return params;
                }
            };
            queue.add(jsonObjectRequest);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
