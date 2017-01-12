package com.malek.books.request;

import android.content.Context;
import android.util.Log;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.StringRequest;
import com.malek.books.entity.Categorie;
import com.malek.books.entity.Livre;
import com.malek.books.entity.Userr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by malek on 18/07/2016.
 */
public class MyRequest {
    private Context context;
    private RequestQueue queue;
    public static String DOMAINE="https://androidmalek.000webhostapp.com";
    public MyRequest(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void getLivres(final LivreCallBack callBack) {
        String url = DOMAINE+"/android/cnx.php";
        final List<Livre> livres=new ArrayList<>();

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray Jsonlivres=response.getJSONArray("res");
                    for (int i=0;i<Jsonlivres.length();i++){

                        Livre livre=new Livre();
                        JSONObject Jsonlivre=Jsonlivres.getJSONObject(i);
                        livre.setNom(Jsonlivre.getString("nom"));
                        livre.setLangue(Jsonlivre.getString("langue"));
                        livre.setAuteur(Jsonlivre.getString("auteur"));
                        livre.setDisc(Jsonlivre.getString("disc"));
                        livre.setId(Jsonlivre.getInt("id"));
                        livre.setImg(Jsonlivre.getString("img"));
                        livre.setNbrPage(Jsonlivre.getString("nbrPage"));
                        livre.setIdUser(Jsonlivre.getInt("idUser"));
                        livre.setIdCat(Jsonlivre.getInt("idCat"));
                        livres.add(livre);

                    }
                    callBack.onSuccess(livres);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               if(error instanceof NetworkError)
                   callBack.onErruer("impossible de se connecter");
            }
        });
        queue.add(jsonObjectRequest);
    }




  public void getCategire(final CategorieCallBack callBack){
      String url = DOMAINE+"/android/categorie.php";
       final List<Categorie> categories=new ArrayList<>();

      JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
              try {
                  JSONArray  jsonArray=response.getJSONArray("res");
                  for (int i=0;i<jsonArray.length();i++){
                      JSONObject jsonObject=jsonArray.getJSONObject(i);
                      Categorie categorie =new Categorie();
                      categorie.setId(jsonObject.getInt("id"));
                      categorie.setNom(jsonObject.getString("nom"));
                      categorie.setImage(jsonObject.getString("image"));
                      categories.add(categorie);

                  }
                  callBack.onSuccess(categories);

              } catch (JSONException e) {
                  Log.d("app","errre json array"+e);
              }


          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
                 callBack.onErreur(error.toString());
          }
      });
      queue.add(jsonObjectRequest);


  }



    public void getUser(final int id, final GetUserCallBack callBack) {
        final String url=DOMAINE+"/android/getUser.php?id="+id;
        final List<Livre> livres=new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray= response.getJSONArray("user");


                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    Userr userr=new Userr();
                    userr.setId(jsonObject.getInt("idUser"));
                    userr.setNom(jsonObject.getString("nom"));
                    userr.setPrenom(jsonObject.getString("prenom"));

                    userr.setEmail(jsonObject.getString("email"));
                    userr.setTel(jsonObject.getString("tel"));
                    Log.d("app","test"+userr.getPrenom());
                    Log.d("app","test"+userr.getEmail());
                    JSONArray jsonArray1=response.getJSONArray("livre");
                    for (int i=0;i<jsonArray1.length();i++){
                        Livre livre=new Livre();
                        JSONObject Jsonlivre=jsonArray1.getJSONObject(i);
                        livre.setNom(Jsonlivre.getString("nom"));
                        livre.setLangue(Jsonlivre.getString("langue"));
                        livre.setAuteur(Jsonlivre.getString("auteur"));
                        livre.setDisc(Jsonlivre.getString("disc"));
                        livre.setId(Jsonlivre.getInt("id"));
                        livre.setImg(Jsonlivre.getString("img"));
                        livre.setIdUser(Jsonlivre.getInt("idUser"));
                        Log.d("APP","live de malek"+livre.getNom());
                        livre.setIdCat(Jsonlivre.getInt("idCat"));
                        livres.add(livre);

                    }
                    userr.setLivres(livres);

                    callBack.onSuccess(userr);

                } catch (JSONException e) {
                    Log.d("app","json malek arreur"+e.toString());
                }








            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Log.d("app","err"+error.toString());
            }
        }) ;


     queue.add(jsonObjectRequest);
    }


    public void ajouter(final Livre livre, final String image,final String id,final AjoutCallBack callback) {

        String url = "http://192.168.122.1/android/ajout.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            Boolean error=jsonObject.getBoolean("erreur");
                            if(!error){
                                callback.onSuccess("Livre bien ajouter");



                            }else {
                                callback.onErreur("Erreur d'ajout");
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
              /*  Log.d("app","test"+livre;
                Log.d("app","test"+userr.getEmail());
                Log.d("app","test"+userr.getPrenom());
                Log.d("app","test"+userr.getTel());
                Log.d("app","test"+userr.getNom());*/



                map.put("nom",livre.getNom());
                map.put("langue",livre.getLangue());
                map.put("disc",livre.getDisc());
                map.put("auteur",livre.getAuteur());
                map.put("nbrPage",livre.getNbrPage());
                map.put("idUser",id);

                if(image!=null) {
                    Log.d("app","image pa nulll");
                    map.put("encoded_string", image);
                }
                return map;
            }
        };
        queue.add(stringRequest);
    }





    public interface LivreCallBack{
        void onSuccess(List<Livre> livres);
        void onErruer(String msg);
    }
    public interface CategorieCallBack{
        void onSuccess(List<Categorie> categories);
        void onErreur(String msg);
    }
    public interface GetUserCallBack{
        void onSuccess(Userr user);
        void onErreur(String msg);
    }
    public interface AjoutCallBack{
        void onSuccess(String msg);
        void onErreur(String msg);
    }
}
