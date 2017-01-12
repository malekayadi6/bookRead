package com.malek.books.request;

import android.content.Context;

import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.malek.books.entity.Userr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by malek on 02/08/2016.
 */
public class InscrireConx {
    private Context context;
    private RequestQueue queue;
    private String KEY_IMAGE = "encoded_string";
    public InscrireConx(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void inscrire(final Userr userr, final InscrireCallback callback){
        String url=MyRequest.DOMAINE+"/android/inscrire.php";
        Log.d("app","url=>"+ url);
        Request request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("app","reponseeeeeeee"+response.toString());
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Boolean error=jsonObject.getBoolean("erreur");
                    if(!error){
                        callback.onSuccess("bien venu");
                    }else {
                        callback.onErreur("Erreur d'inscription");
                    }
                } catch (JSONException e) {
                    Log.d("app","erreur inscrire"+e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("app","erreur inscrire"+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("nom",userr.getNom());
                map.put("prenom",userr.getPrenom());
                map.put("email",userr.getEmail());
                map.put("pwd",userr.getPwd());
                map.put("tel",userr.getTel());
                return map;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(request);


    }
    public interface InscrireCallback{
        void onSuccess(String msg);
        void onErreur(String msg);
    }
    public interface ConxCallBack{
        void onSuccess(String msg,Userr userr);
        void onErreur(String msg);
    }


    public void cnx(final String email, final String pwd, final ConxCallBack callback) {

        String url = "http://192.168.122.1/android/connexion.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("app","resulata"+ s);
                        try {


                            JSONObject json=new JSONObject(s);
                            Boolean error=json.getBoolean("erreur");
                            if(!error){
                                JSONObject jsonObject=json.getJSONObject("users");
                                Userr userr=new Userr();
                                userr.setId(jsonObject.getInt("idUser"));
                                userr.setNom(jsonObject.getString("nom"));
                                userr.setPrenom(jsonObject.getString("prenom"));
                                userr.setImage(jsonObject.getString("image"));
                                Log.d("app","userrrr" +userr.getNom());

                                userr.setEmail(jsonObject.getString("email"));
                                userr.setTel(jsonObject.getString("tel"));
                                callback.onSuccess("Bien venu " +userr.getNom() + " " +userr.getPrenom(),userr);
                                //ici session :D


                            }else {
                                callback.onErreur("Email ou mot de passe incorrect ...");
                            }
                        } catch (JSONException e) {
                            Log.d("app","erreur inscrire"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("app","ereruuur   ===> "+volleyError.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("email",email);
                map.put("pwd",pwd);
                return map;
            }
        };
        queue.add(stringRequest);
    }

    public void insc(final Userr userr, final String image, final InscrireCallback callback) {

        String url = MyRequest.DOMAINE+"/android/inscrire2.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            Boolean error=jsonObject.getBoolean("erreur");
                            if(!error){
                                callback.onSuccess("bien venu");
                                //ici session :D


                            }else {
                                callback.onErreur("Erreur d'inscription");
                            }
                        } catch (JSONException e) {
                            Log.d("app","erreur inscrire"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("app","ereruuur   ===> "+volleyError.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                Log.d("app","test"+userr.getPwd());
                Log.d("app","test"+userr.getEmail());
                Log.d("app","test"+userr.getPrenom());
                Log.d("app","test"+userr.getTel());
                Log.d("app","test"+userr.getNom());



                map.put("nom",userr.getNom());
                map.put("prenom",userr.getPrenom());
                map.put("email",userr.getEmail());
                map.put("pwd",userr.getPwd());
                map.put("tel",userr.getTel());

                if(image!=null) {
                    Log.d("app","image pa nulll");
                    map.put(KEY_IMAGE, image);
                }
                return map;
            }
        };
        queue.add(stringRequest);
    }
}
