package com.malek.books;

import android.content.Context;
import android.content.SharedPreferences;

import com.malek.books.entity.Userr;

/**
 * Created by malek on 28/11/16.
 */

public class SessionManager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final static String PREF_NAME="app_books";
    private final static int PRIVATE_MODE=0;
    private final static String IS_LOGGED="isLogged";
    private final static String USER_ID="idUder";
    private final static String USER_NAME="userName";
    private final static String USER_PRENOM="userPrenom";
    private final static String USER_IMAGE="userImage";
    private final static String USER_TEL="userTel";
    private final static String USER_EMAIL="userEmail";


    private Context context;

   public SessionManager(Context context){
        this.context=context;
       preferences=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
       editor=preferences.edit();
    }
    public boolean isLooged(){
        return preferences.getBoolean(IS_LOGGED,false);
    }
    public String getNom(){
        return preferences.getString(USER_NAME,null);
    }
    public String getPrenom(){
        return preferences.getString(USER_PRENOM,null);
    }
    public String getTel(){
        return preferences.getString(USER_TEL,null);

    }
    public String getEmail(){
        return preferences.getString(USER_EMAIL,null);

    }
    public String getImage(){
        return preferences.getString(USER_IMAGE,null);

    }
    public String getId(){
        return preferences.getString(USER_ID,null);

    }

    public void setUserss(Userr userr){
           editor.putString(USER_NAME,userr.getNom());
        editor.putString(USER_PRENOM,userr.getPrenom());
        editor.putString(USER_EMAIL,userr.getEmail());
        editor.putString(USER_TEL,userr.getTel());
        editor.putString(USER_ID,String.valueOf(userr.getId()));
        editor.putString(USER_IMAGE,userr.getImage());
        editor.putBoolean(IS_LOGGED,true);
        editor.commit();
    }
    public void logOut(){
        editor.clear().commit();
    }


}
