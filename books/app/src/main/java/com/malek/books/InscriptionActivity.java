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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.malek.books.entity.Userr;
import com.malek.books.request.InscrireConx;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InscriptionActivity extends AppCompatActivity {

    private TextInputLayout tilNom,tilPrenom,tilEmail,tilPwd,tilCPwd,tilTel;
    private Button btnInscrire;
    private Userr user;
    private RequestQueue queue;
    private int PICK_IMAGE_REQUEST =1;
    private Bitmap bitmap;
    private ImageView imageView;
    private SessionManager sessionManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionManager=new SessionManager(this);

        tilNom= (TextInputLayout) findViewById(R.id.til_nomI);
        tilPrenom= (TextInputLayout) findViewById(R.id.til_prenomI);
        tilEmail= (TextInputLayout) findViewById(R.id.til_emailI);
        tilPwd= (TextInputLayout) findViewById(R.id.til_pwdI);
        tilCPwd= (TextInputLayout) findViewById(R.id.til_cPwdI);
        tilTel= (TextInputLayout) findViewById(R.id.til_telI);
        btnInscrire= (Button) findViewById(R.id.btn_inscrire);
        imageView= (ImageView) findViewById(R.id.photo);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        queue= MySingleton.getInstance(this).getRequestQueue();


        btnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String nom,prenom,email,pwd,cPwd,tel;
                   Boolean erruer=false;
                   user=new Userr();

                nom=tilNom.getEditText().getText().toString().trim();
                email=tilEmail.getEditText().getText().toString().trim();
                prenom=tilPrenom.getEditText().getText().toString().trim();
                pwd=tilPwd.getEditText().getText().toString().trim();
                cPwd=tilCPwd.getEditText().getText().toString().trim();
                tel=tilTel.getEditText().getText().toString().trim();
                try {
                    validNom(nom);
                    user.setNom(nom);
                    tilNom.setErrorEnabled(false);
                    erruer=true;

                } catch (Exception e) {
                    tilNom.setError(e.getMessage());
                    erruer=false;
                }
                try {
                    validPrenom(prenom);
                    user.setPrenom(prenom);
                    tilPrenom.setErrorEnabled(false);
                    erruer=true;

                } catch (Exception e) {
                    tilPrenom.setError(e.getMessage());
                    erruer=false;

                }


               try {
                    validEmail(email);
                    tilEmail.setErrorEnabled(false);
                   user.setEmail(email);
                   erruer=true;
               } catch (Exception e) {
                   tilEmail.setError(e.getMessage());
                   erruer=false;

               }
                try {
                    validTel(tel);
                    user.setTel(tel);
                    tilTel.setErrorEnabled(false);
                    erruer=true;
                } catch (Exception e) {
                    tilTel.setError(e.getMessage());
                    erruer=false;

                }
               // user.setPwd(pwd);
   Log.d("app","pwd="+pwd);
                Log.d("app","cpwd"+cPwd);

                try {
                    validPassword(pwd,cPwd);
                    user.setPwd(pwd);
                    tilCPwd.setErrorEnabled(false);
                    tilPwd.setErrorEnabled(false);
                    erruer=true;

                } catch (Exception e) {
                    tilPwd.setError(e.getMessage());
                    tilCPwd.setError(e.getMessage());
                    tilPwd.getEditText().setText("");
                    tilCPwd.getEditText().setText("");
                    erruer=false;

                }

                if (erruer){
                 InscrireConx inscrireConx=new InscrireConx(InscriptionActivity.this,queue);
                  Log.d("app","usersss"+user.getPwd());

                    String image=null;
                    if (bitmap!=null){
                        image=getStringImage(bitmap);
                    }
                  inscrireConx.insc(user,image, new InscrireConx.InscrireCallback() {
                        @Override
                        public void onSuccess(String msg) {
                            Toast.makeText(InscriptionActivity.this,msg,Toast.LENGTH_LONG).show();
                            sessionManager.setUserss(user);
                            Intent intent=new Intent(InscriptionActivity.this, MainActivity.class);

                            startActivity(intent);
                        }

                        @Override
                        public void onErreur(String msg) {

                            Toast.makeText(InscriptionActivity.this,msg,Toast.LENGTH_LONG).show();
                        }
                    });
                  Log.d("app","okkkkkkkkkkkkkkkkkk");

                }else Toast.makeText(InscriptionActivity.this,"Erreur de saisie !!",Toast.LENGTH_LONG).show();
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

















    public void validEmail(String email) throws Exception{
        if(email!=null && email.trim().length()!=0){
            if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) throw new Exception("L'email n'est pas valide.Voulez-vous réessayer ?");

        }else throw new Exception("Merci d'entreé votre Email !");

    }
    public void validTel(String tel) throws Exception{
        if(tel!=null && tel.trim().length()!=0){
            if(!tel.matches("^\\d+$")) throw new Exception("votre numero telephone doit uniquemnt contient des chiffres .Voulez-vous réessayer ?");
        }else throw new Exception("Merci d'entreé votre numero de telephone !");
    }
    public void validNom (String nom) throws Exception{
        if(nom!=null&& nom.trim().length()!=0){
            if(nom.trim().length()<2) throw new Exception("Le nom d'utilisateur doit contenir au mojn 2 caratère.Voulez-vous réessayer ?");
        }else throw new Exception("Merci d'entreé le nom d'utilisateur.Voulez-vous réessayer ?");
    }
    public void validPrenom(String nom) throws Exception{
        if(nom!=null && nom.trim().length()<2)throw new Exception("Le prenom doit etre au moin contenir 2 caratère.Voulez-vous réessayer ?");

    }
    private void validPassword(String password , String confPassword) throws Exception {
        if (password.trim().length() > 0 && confPassword.trim().length() > 0) {
            if (password.trim().length() > 7) {
                if (!password.equals(confPassword))
                    throw new Exception("Les mots de passe ne correspondent pas. Voulez-vous réessayer ?");
            } else
                throw new Exception("Les mots de passe doit contenir aux moin 7 caractére. Voulez-vous réessayer ?");

        } else
            throw new Exception("Merci de siasie votre mots de passe et la confirmations.Voulez-vous réessayer ?");




    }

}
