package com.example.photogram;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
   
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photogram.interfaces.FragmentsInterface;
import com.example.photogram.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements FragmentsInterface {

    public static Usuario usuario;
    public static String uid;

    public DatabaseReference reference;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reference = FirebaseDatabase.getInstance().getReference();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_bar);
       // bottomNavigationView.setItemHorizontalTranslationEnabled(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                onOptionsItemSelected(menuItem);
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_principal, new HomeFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.btn_home_menu);
        bottomNavigationView.setItemHorizontalTranslationEnabled(true);
        uid = getIntent().getStringExtra("idUsuario");
    }

    @Override
    public void onResume(){
        super.onResume();
        asignarUsuario(uid);
        Log.e("SE OBTUVO EL USUARIO", "Se obtuvo el id: " +uid);
    }

    public void asignarUsuario(String uid){
        reference.child("Usuarios").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuario = (Usuario) dataSnapshot.getValue(Usuario.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bottomnavigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment seleccion=null;

       switch (id) {
           case R.id.btn_buscar_menu :
               seleccion = new BuscarFragment();

               break;
           case  R.id.btn_home_menu :
                seleccion = new HomeFragment();

               break;
           case R.id.btn_home_chats :
               seleccion = new ChatsFragment();
               break;
           case R.id.btn_miperfil_menu:
               seleccion = new MiPerfilFragment();
               break;
           }
        //return super.onOptionsItemSelected(item);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_principal, seleccion).commit();
        return true;
    }

    //Estos don son los metodos que se implementan con la interface de los fragments

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}
