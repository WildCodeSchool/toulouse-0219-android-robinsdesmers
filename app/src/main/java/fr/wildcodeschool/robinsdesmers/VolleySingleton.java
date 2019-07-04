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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.wildcodeschool.robinsdesmers.model.CollectPointItem;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class VolleySingleton {
    private static final String REQUEST_URL = "http://10.0.2.2:8080/";
    //private static final String REQUEST_URL = "http://192.168.8.119:8080/";
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
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public void postRubbish(RubbishItem rubbishItem, User user, final Consumer<RubbishItem> rubbishListener) {
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
                rubbishListener.accept(rubbishItem1);
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
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void getAllRubbish(final Consumer<List<RubbishItem>> rubbishListener) {
        String url = REQUEST_URL + "rubbishes";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<RubbishItem> rubbishItemList = new ArrayList<>();
                        if (response.length() > 0) {
                            rubbishItemList = Arrays.asList(gson.fromJson(response.toString(), RubbishItem[].class));
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

    public void postCollectPoint(final CollectPointItem collectPointItem, User user, final Consumer<CollectPointItem> collectPointItemConsumer) {
        String url = REQUEST_URL + "users/" + user.getId() + "/collectPoints";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(collectPointItem);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", String.valueOf(response));
                CollectPointItem collectPointItem1 = gson.fromJson(response.toString(), CollectPointItem.class);
                collectPointItemConsumer.accept(collectPointItem1);
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
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void getAllCollectPoint(final Consumer<List<CollectPointItem>> collectPointListener) {
        String url = REQUEST_URL + "collectPoints";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<CollectPointItem> collectPointItems = new ArrayList<>();
                        if (response.length() > 0) {
                            collectPointItems = Arrays.asList(gson.fromJson(response.toString(), CollectPointItem[].class));
                        }
                        collectPointListener.accept(collectPointItems);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void getAllUsers(final Consumer<List<User>> userListener) {
        String url = REQUEST_URL + "users";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<User> usersList = new ArrayList<>();
                        if (response.length() > 0) {
                            usersList = Arrays.asList(gson.fromJson(response.toString(), User[].class));
                        }
                        userListener.accept(usersList);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void postUser(User user, final Consumer<User> userListener) {

        String url = REQUEST_URL + "users";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(user);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", String.valueOf(response));
                User user1 = gson.fromJson(response.toString(), User.class);
                userListener.accept(user1);
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
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void getOneUser(Long userId, final Consumer<User> userListener) {
        String url = REQUEST_URL + "users/" + userId;
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        User user = gson.fromJson(response.toString(), User.class);
                        userListener.accept(user);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void collectRubbish(Long rubbishId, final Consumer<RubbishItem> rubbishListener) {
        String url = REQUEST_URL + "rubbishes/" + rubbishId + "/collected";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        RubbishItem rubbishItem = gson.fromJson(response.toString(), RubbishItem.class);
                        rubbishListener.accept(rubbishItem);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void updateUser(Long userId, User user, final Consumer<User> userListener) {
        String url = REQUEST_URL + "users/" + userId;
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(user);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        User user = gson.fromJson(response.toString(), User.class);
                        userListener.accept(user);
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
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void getOneRubbish(Long rubbishId, final Consumer<RubbishItem> rubbishListener) {
        String url = REQUEST_URL + "rubbishes/" + rubbishId;
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        RubbishItem rubbishItem = gson.fromJson(response.toString(), RubbishItem.class);
                        rubbishListener.accept(rubbishItem);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void deleteOneCollectPoint(Long collectPointId, final Consumer<CollectPointItem> collectPointItemConsumer) {
        String url = REQUEST_URL + "collectPoints/" + collectPointId;
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", String.valueOf(response));
                CollectPointItem collectPointItem1 = gson.fromJson(response.toString(), CollectPointItem.class);
                collectPointItemConsumer.accept(collectPointItem1);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}