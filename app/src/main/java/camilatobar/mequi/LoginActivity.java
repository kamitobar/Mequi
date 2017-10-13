package camilatobar.mequi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    EditText eCorreo, eContrasena;
    TextView tRegistrar;
    Button bIniciar;
    String link2, sangre, Correo2, nombre, documento, scorreo, scontrasena, sexo, correo2, nombre2, sangre2, documento2, userid, userid2;
    private FirebaseAuth mAuth;
    //    Bitmap foto_perfil;
    FirebaseDatabase database3;
    DatabaseReference myRef3;
    Manual correoclass;
    ArrayList<Manual> info;
    private static final String TAG = "LoginActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    String optLog="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database3 = FirebaseDatabase.getInstance();

        info = new ArrayList<Manual>();

//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                myRef3 = database3.getReference("Datos");
//                mAuth = FirebaseAuth.getInstance();
//                if (user != null) {  //cuando ya hay unn usuario loggeado
//                    userid = mAuth.getCurrentUser().getUid();
//                    myRef3.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (userid.equals("auOvjKwIrqQ9Wtazh7I6wK2m0wt1")){
//                                intent = new Intent(LoginActivity.this, DoctorActivity.class);
//                                intent.putExtra("user", userid);
//                                startActivity(intent);
//                                finish();
//                            }
//                            else if (dataSnapshot.child(userid).exists()) { //cuando se ha creado en la base de datos entra acá
//                                info.add(dataSnapshot.child(userid).getValue(Correo.class));
//                                nombre2 = info.get(0).getNombre();
//                                sangre2 = info.get(0).getSangre();
//                                correo2 = info.get(0).getCorreo();
//                                documento2 = info.get(0).getDocumento();
//                                /*editor.putString("sangre", sangre2);
//                                editor.putString("nombre", nombre2);
//                                editor.putString("documento", documento2);
//                                editor.putString("correo", correo2);
//                                editor.commit();*/
//                                intent = new Intent(LoginActivity.this, PerfilActivity.class);;
//                                startActivity(intent);//lo envia al perfil
//                                finish();
//
//                            }else{
//                                intent = new Intent(LoginActivity.this, RegistroActivity.class);
//                                optLog="2"; //si el usuario está loggeado pero por ejemplo canceló el proceso de llenar el resto de info
//                                intent.putExtra("optLog", optLog);
//                                startActivity(intent);
//                                //finish();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            Toast.makeText(LoginActivity.this, "El userid no existe",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//
//                    // User is signed in
//                    // goNextActivity();
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//            }
//        };



        eContrasena = (EditText) findViewById(R.id.ePass);
        bIniciar = (Button) findViewById(R.id.bIniciar);
        tRegistrar = (TextView) findViewById(R.id.tRegistro);
        eCorreo = (EditText) findViewById(R.id.eUser);

        tRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this ,RegistroActivity.class);
//                intent.putExtra("optLog", optLog);
                startActivity(intent);
            }
        });

        bIniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myRef3 = database3.getReference("Datos");
                mAuth = FirebaseAuth.getInstance();
                if(eCorreo.getText().toString().equals("") || eContrasena.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Llene los campos requeridos",Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.signInWithEmailAndPassword(eCorreo.getText().toString(), eContrasena.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "El usuario ingresado no existe",
                                        Toast.LENGTH_SHORT).show();
                            } else {

                                userid = mAuth.getCurrentUser().getUid();

                                    //  editor.putInt("login",1);
                                    //  editor.commit();
                                   // intent = new Intent(LoginActivity.this, PerfilActivity.class);
                                    //intent.putExtra("user", userid);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(LoginActivity.this, "Bienvenido",
                                            Toast.LENGTH_SHORT).show();

                                    myRef3.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.child(userid).exists()) {
                                                info.add(dataSnapshot.child(userid).getValue(Manual.class));
                                                nombre2 = info.get(0).getNombre();
                                               // sangre2 = info.get(0).getSangre();
                                                link2 = info.get(0).getLink();
                                                //documento2 = info.get(0).getDocumento();
                                              /*  editor.putString("sangre", sangre2);
                                                editor.putString("nombre", nombre2);
                                                editor.putString("documento", documento2);
                                                editor.putString("correo", correo2);
                                                editor.commit();*/

                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            Toast.makeText(LoginActivity.this, "El userid no existe",
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                    });

                            }
                        }
                    });

                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1234 && resultCode==RESULT_OK)
        {
            Toast.makeText(this, "Exito", Toast.LENGTH_SHORT).show(); //Ojo que Toast tiene un retardo, puede hacer la app lenta
        }
        else
        {
            if (requestCode==1234 && resultCode==RESULT_CANCELED)
            {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show(); //Ojo que Toast tiene un retardo, puede hacer la app lenta
            }
        }
    }


}
