package com.malek.books.FragmentsPrincipale;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.malek.books.MainActivity;
import com.malek.books.MySingleton;
import com.malek.books.R;
import com.malek.books.entity.Userr;
import com.malek.books.request.InscrireConx;

/**
 * A simple {@link Fragment} subclass.
 */
public class InscriptionFragment extends Fragment {
    private TextInputLayout tilNom,tilPrenom,tilEmail,tilPwd,tilCPwd,tilTel;
    private Button btnInscrire;
    private Userr user;
    private RequestQueue queue;


    public InscriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;

        view= inflater.inflate(R.layout.fragment_inscription, container, false);



        tilNom= (TextInputLayout) view.findViewById(R.id.til_nomI);
        tilPrenom= (TextInputLayout) view.findViewById(R.id.til_prenomI);
        tilEmail= (TextInputLayout) view.findViewById(R.id.til_emailI);
        tilPwd= (TextInputLayout) view.findViewById(R.id.til_pwdI);
        tilCPwd= (TextInputLayout) view.findViewById(R.id.til_cPwdI);
        tilTel= (TextInputLayout) view.findViewById(R.id.til_telI);
        btnInscrire= (Button) view.findViewById(R.id.btn_inscrire);

        queue= MySingleton.getInstance(getContext()).getRequestQueue();


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
                } catch (Exception e) {
                    tilNom.setError(e.getMessage());
                    erruer=true;
                }
                try {
                    validPrenom(prenom);
                    user.setPrenom(prenom);
                    tilPrenom.setErrorEnabled(false);

                } catch (Exception e) {
                    tilPrenom.setError(e.getMessage());
                    erruer=true;

                }

               try {
                    validEmail(email);
                   user.setEmail(email);
                   tilEmail.setErrorEnabled(false);

                } catch (Exception e) {
                   tilEmail.setError(e.getMessage());
                }
                try {
                    validTel(tel);

                    user.setTel(tel);
                    tilTel.setErrorEnabled(false);
                } catch (Exception e) {
                    tilTel.setError(e.getMessage());
                    erruer=true;

                }
                try {
                    validPassword(pwd,cPwd);
                    user.setPwd(pwd);
                    tilCPwd.setErrorEnabled(false);
                    tilCPwd.setErrorEnabled(false);
                } catch (Exception e) {
                    tilPwd.setError(e.getMessage());
                    tilCPwd.setError(e.getMessage());
                    tilPwd.getEditText().setText("");
                    tilCPwd.getEditText().setText("");
                }

                if (!erruer){
                    InscrireConx inscrireConx=new InscrireConx(getContext(),queue);
                    inscrireConx.inscrire(user, new InscrireConx.InscrireCallback() {
                        @Override
                        public void onSuccess(String msg) {
                            Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onErreur(String msg) {

                        }
                    });
                }
            }
        });

        return view;
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
