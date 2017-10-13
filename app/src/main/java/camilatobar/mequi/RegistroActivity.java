package camilatobar.mequi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    EditText eDocumento, eNombre, eTelefono, eCorreo, eAlergias, eEnfermedades, eAcudiente, eTelAcudiente, eContrasena, eR_contrasena;
    String  sangre, documento, nombre, contra, correo, userid, contraR;
    Button benviar, bcancelar;

    FirebaseDatabase database, database3;
    DatabaseReference myRef, myRef3;
    Usuarios usuarios, usuarios2;
    //Correo correoclass;
    String optLog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

//        Bundle extras = getIntent().getExtras();
//        optLog = extras.getString("optLog");

        eCorreo = (EditText) findViewById(R.id.eRcorreo);
        eContrasena = (EditText) findViewById(R.id.eRpass);
        eR_contrasena = (EditText) findViewById(R.id.eRrepass);
        benviar = (Button) findViewById(R.id.bRregistrar);
        bcancelar = (Button) findViewById(R.id.bRcancelar);

//        if(optLog.equals("2") || optLog.equals("1")) {
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            //LoginManager.getInstance().logInWithReadPermissions(RegistroActivity.this, Arrays.asList("email"));
//            eCorreo.setHint("");
//            eCorreo.setEnabled(false);
//            eContrasena.setHint("");
//            eR_contrasena.setHint("");
//            eContrasena.setEnabled(false);
//            eR_contrasena.setEnabled(false);
//        }
        benviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent();

                database3 = FirebaseDatabase.getInstance();
                mAuth = FirebaseAuth.getInstance();
                correo = eCorreo.getText().toString();
                //database = FirebaseDatabase.getInstance();
                //nombre = eNombre.getText().toString();
                contra = eContrasena.getText().toString();
                contraR = eR_contrasena.getText().toString();

                    if( eContrasena.getText().toString().equals("") || eR_contrasena.getText().toString().equals("") || eCorreo.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Llene los campos obligatorios",Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED, intent);
                    } else if(!(eContrasena.getText().toString().equals(eR_contrasena.getText().toString()))){
                        Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED, intent);
                        //finish();
                    }else {

                        mAuth.createUserWithEmailAndPassword(correo, contra)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Intent intent = new Intent();
                                        if (!task.isSuccessful()) {

                                            setResult(RESULT_CANCELED, intent);
                                            //finish();
                                            Toast.makeText(RegistroActivity.this, "Ingrese un correo y contraseña validos",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
//                                            userid = mAuth.getCurrentUser().getUid();
//                                            myRef = database.getReference("Usuarios").child(String.valueOf(documento));
//                                            usuarios = new Usuarios(String.valueOf(correo), nombre, telefono, documento, sexo, sangre, alergias, enfermedades, acudiente, tel_acudiente);
//                                            myRef.setValue(usuarios);
//                                            myRef3 = database3.getReference("Datos").child(String.valueOf(userid));
//                                            correoclass = new Correo(String.valueOf(correo), nombre, telefono, documento, sexo, sangre, alergias, enfermedades, acudiente, tel_acudiente);
//                                            myRef3.setValue(correoclass);

                                            setResult(RESULT_OK, intent);
                                            finish();

                                            Toast.makeText(RegistroActivity.this, "Proceso exitoso",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

//                    setResult(RESULT_OK, intent);
//                    finish();
                    }



//                    setResult(RESULT_OK, intent);
//                    finish();
            }
        });
        bcancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
