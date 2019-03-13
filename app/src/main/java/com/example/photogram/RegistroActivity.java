package com.example.photogram;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.photogram.model.Usuario;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    EditText etNombre, etMail, etPass, etConfirmPass;
    ImageView ivPerfil;
    Button botonRegistro;

    Uri pathImagen;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText) findViewById(R.id.et_nombre_registrar);
        etMail = (EditText) findViewById(R.id.et_email_registrar);
        etPass = (EditText) findViewById(R.id.et_password_regitrar);
        etConfirmPass = (EditText) findViewById(R.id.et_confirmarpassword_regitrar);
        ivPerfil = (ImageView) findViewById(R.id.ImageView_perfil_registrar);
        botonRegistro = (Button) findViewById(R.id.boton_registrar);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagen();
            }
        });

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

    }

    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Selecciona tu foto de perfil"), 10);
    }

    public void onActivityResult(int reqCode, int resCode, Intent data){
        //super(reqCode,resCode, data);
        if(resCode == RESULT_OK){
            Uri uri = data.getData();
            Glide.with(this).load(uri).centerInside().circleCrop().into(ivPerfil);
            pathImagen = uri;
        }
    }

    public void registrar(){
        String mail = etMail.getText().toString();
        String pass = etPass.getText().toString();
        if(validaciones(mail, pass)){
            crearCuenta(mail, pass);
        }
    }

    public boolean validaciones(String mail, String pass){
        if(validarMail(mail)){
            if(validarPassword(pass)){
                return true;
            }
        }else{
            etMail.setError("Ingresa una dirección de correo electrónico válida");
        }
        return false;
    }

    public boolean validarMail(String mail){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(mail).matches();
    }

    public boolean validarPassword(String pass){
        if(etPass.getText().toString().length() >= 6) {
            if (pass.equals(etConfirmPass.getText().toString())) {
                return true;
            }else{
                etConfirmPass.setError("Las contraseñas no coincides");
            }
        }else{
            etPass.setError("La contraseña debe tener mas de 6 caracteres");
        }
        return false;
    }

    public void crearCuenta(String mail, String pass){
        auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = auth.getCurrentUser();
                    crearYSubirUsuario(user);

                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(RegistroActivity.this, "Atenticación Fallida.",
                            Toast.LENGTH_SHORT).show();
                    crearYSubirUsuario(null);
                }

                // ...
            }
        });
    }

    private void crearYSubirUsuario(FirebaseUser user) {
        final String idUser = user.getUid();
        Uri file = pathImagen;

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        final StorageReference storageReference = storageRef.child(idUser+"/FotosPerfil/"+file.getLastPathSegment());
        UploadTask uploadTask = storageReference.putFile(file);

        progressBar.setVisibility(View.VISIBLE);
        botonRegistro.setClickable(false);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return storageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    crearSubir(task.getResult().toString(), idUser);
                } else {
                    // Handle failures
                }
            }
        });
    }

    public void crearSubir(String url, String idUser){
        Usuario usuario = new Usuario(url,etNombre.getText().toString(), idUser);
        reference.child("Usuarios").child(idUser).setValue(usuario);
        progressBar.setVisibility(View.INVISIBLE);
        Intent i = new Intent(RegistroActivity.this, MainActivity.class);
        i.putExtra("idUsuario", idUser);
        startActivity(i);
        finish();
    }

}
