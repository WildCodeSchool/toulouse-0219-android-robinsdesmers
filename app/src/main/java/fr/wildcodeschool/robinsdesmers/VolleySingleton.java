package fr.wildcodeschool.robinsdesmers;

import android.content.Context;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class VolleySingleton {
    private static final String REQUEST_URL = "http://10.0.2.2:8080/";
    private static VolleySingleton instance;
    private static Context ctx;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public void postRubbish(RubbishItem rubbishItem, User user) {

        String url = REQUEST_URL + "users/" + user.getId() + "/rubbishes";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(rubbishItem);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", String.valueOf(response));
                RubbishItem rubbishItem1 = gson.fromJson(response.toString(), RubbishItem.class);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void getAllRubbish(final Consumer<List<RubbishItem>> rubbishListener) {
        String url = REQUEST_URL + "rubbishes";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        //final JSONObject jsonObject = null;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<RubbishItem> rubbishItemList = new ArrayList<>();
                        if (response.length() > 0) {
                             rubbishItemList = Arrays.asList(gson.fromJson(response.toString(), RubbishItem[].class));
                            /* TODO : suppression du code mort
                               for (RubbishItem rubbishItem : rubbishItemList) {
                                // GOT THE OBJECT of PEOPLE
                                String title = rubbishItem.getTitle();
                                String description = rubbishItem.getDescription();
                                String sumRubbish = rubbishItem.getSumRubbish().toString();
                                boolean collected = rubbishItem.isCollected();
                                Double latitude = rubbishItem.getLatitude();
                                Double longitude = rubbishItem.getLongitude();
                            }*/
                        }
                        rubbishListener.accept(rubbishItemList);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}