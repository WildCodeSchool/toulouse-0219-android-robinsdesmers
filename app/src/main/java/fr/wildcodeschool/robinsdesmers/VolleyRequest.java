package fr.wildcodeschool.robinsdesmers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.wildcodeschool.robinsdesmers.inscription.AvatarChoicesActivity;
import fr.wildcodeschool.robinsdesmers.model.User;

public abstract class VolleyRequest extends Context {

    public static User getInscriptionActivityDetails(Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // TODO : URL de la requête vers l'API
        String url = "http://10.0.2.2/users";

        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        // On ajoute la requête à la file d'attente
        requestQueue.add(jsonArrayRequest);

        return user;
    }


}
