package com.example.photogram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText etMail, etPassword;
    Button botonLogin;
    TextView tvCrearCuenta;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = (EditText) findViewById(R.id.editText_login_login);
        etPassword = (EditText) findViewById(R.id.edittext_password_login);
        botonLogin= (Button) findViewById(R.id.button_login_login);
        tvCrearCuenta = (TextView) findViewById(R.id.textView_crearCuenta);

        auth = FirebaseAuth.getInstance();

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etMail.getText().toString();
                String password = etPassword.getText().toString();
                verificarEmailPassword(email, password);
            }
        });

        tvCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void verificarEmailPassword(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = auth.getCurrentUser();
                    ingresar(user);
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Autenticacion Fallida.",
                            Toast.LENGTH_SHORT).show();
                    ingresar(null);
                }

                // ...
            }
        });
    }

    private void ingresar(FirebaseUser user) {
        if(user!=null) {
            String uid = user.getUid();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("idUsuario", uid);
            startActivity(intent);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("idUsuario", user.getUid());
            startActivity(intent);
            finish();
        }
    }

}
