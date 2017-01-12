package com.malek.books;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.malek.books.entity.Userr;
import com.malek.books.request.InscrireConx;
import com.malek.books.request.MyRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    private TextInputLayout tilEmail,tilPwd;
    private Button cnx;
    private RequestQueue queue;
    private InscrireConx inscrireConx;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tilEmail= (TextInputLayout) findViewById(R.id.til_mail);
        tilPwd= (TextInputLayout) findViewById(R.id.til_pwdd);
        cnx= (Button) findViewById(R.id.cnx);
        queue= MySingleton.getInstance(this).getRequestQueue();
        sessionManager=new SessionManager(this);
        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inscrireConx=new InscrireConx(LoginActivity.this,queue);
                inscrireConx.cnx(tilEmail.getEditText().getText().toString(), tilPwd.getEditText().getText().toString(), new InscrireConx.ConxCallBack() {
                    @Override
                    public void onSuccess(String msg, Userr userr) {
                        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
                        Log.d("testttttttttttttttttttttttttttttttt",userr.getNom());
                        sessionManager.setUserss(userr);
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onErreur(String msg) {
                        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();

                    }
                });

            }
        });


    }

/*
    private void uploadImage(){
        String url = "http://"+ MyRequest.DOMAINE+"/android/upload.php";

        //Showing the progress dialog
       // final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
              //          loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(LoginActivity.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                       // loading.dismiss();

                        //Showing toast
                        Toast.makeText(LoginActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String name = editText.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };


        requestQueue.add(stringRequest);
    }





    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
*/
}
