package com.malek.books;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.malek.books.entity.Livre;
import com.malek.books.request.InscrireConx;
import com.malek.books.request.MyRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PsoteLivre extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST =1;
    private Bitmap bitmap;
    private ImageView imageView;
    private Button button;
    private TextInputLayout tilNom,tilLangue,tilAuteur,tilNbrPage,tilDisc;
    private RequestQueue queue;

    private SessionManager sessionManager;
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psote_livre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView= (ImageView) findViewById(R.id.photo);
        button= (Button) findViewById(R.id.btn_ajout);
        tilNom= (TextInputLayout) findViewById(R.id.til_nomLivre);
        tilLangue= (TextInputLayout) findViewById(R.id.til_langue);
        tilAuteur= (TextInputLayout) findViewById(R.id.auteur_tl);
        tilNbrPage= (TextInputLayout) findViewById(R.id.page_tl);
        tilDisc= (TextInputLayout) findViewById(R.id.til_disc);

        queue= MySingleton.getInstance(this).getRequestQueue();
        sessionManager=new SessionManager(this);
       id=sessionManager.getId();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom, langue, auteur, disc, nbrPage;
                Boolean erruer = false;
                nom = tilNom.getEditText().getText().toString().trim();
                langue = tilLangue.getEditText().getText().toString().trim();
                auteur = tilAuteur.getEditText().getText().toString().trim();
                nbrPage = tilNbrPage.getEditText().getText().toString().trim();
                disc = tilDisc.getEditText().getText().toString().trim();
                Livre livre = new Livre();


                try {
                    validNom(nom);
                    livre.setNom(nom);
                    tilNom.setErrorEnabled(false);
                    erruer = true;

                } catch (Exception e) {
                    tilNom.setError(e.getMessage());
                    erruer = false;
                }
                try {
                    validNom(auteur);
                    livre.setAuteur(auteur);
                    tilAuteur.setErrorEnabled(false);
                    erruer = true;

                } catch (Exception e) {
                    tilAuteur.setError(e.getMessage());
                    erruer = false;
                }
                try {
                    validNom(nbrPage);
                    livre.setNbrPage(nbrPage);
                    tilNbrPage.setErrorEnabled(false);
                    erruer = true;

                } catch (Exception e) {
                    tilNbrPage.setError(e.getMessage());
                    erruer = false;
                }
                try {
                    validNom(langue);
                    livre.setLangue(langue);
                    tilLangue.setErrorEnabled(false);
                    erruer = true;

                } catch (Exception e) {
                    tilLangue.setError(e.getMessage());
                    erruer = false;
                }
                try {
                    validNom(disc);
                    livre.setDisc(disc);
                    tilDisc.setErrorEnabled(false);
                    erruer = true;

                } catch (Exception e) {
                    tilDisc.setError(e.getMessage());
                    erruer = false;
                }
                Log.d("app","livrees"+livre.getNom()+" "+livre.getAuteur()+" "+livre.getImg()+" "+livre.getLangue()+" "+livre.getNbrPage()+" "+livre.getDisc());

                if (erruer) {
                    InscrireConx inscrireConx = new InscrireConx(PsoteLivre.this, queue);
                    Log.d("app", "usersss" + livre.getNom());

                    String image = null;
                    if (bitmap != null) {
                        image = getStringImage(bitmap);
                    }
                    MyRequest request = new MyRequest(PsoteLivre.this, queue);
                    request.ajouter(livre,image,id, new MyRequest.AjoutCallBack() {
                        @Override
                        public void onSuccess(String msg) {
                            Log.d("app", "usersss" + msg);
                        }

                        @Override
                        public void onErreur(String msg) {
                            Log.d("app", "usersss" + msg);
                        }
                    });


                }
            }


        });

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




    public void validNom (String nom) throws Exception{
        if(nom==null || nom.trim().length()==0)
        throw new Exception("champ vide .Voulez-vous réessayer ?");
    }
}
