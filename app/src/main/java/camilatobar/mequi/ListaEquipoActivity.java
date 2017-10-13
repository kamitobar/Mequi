package camilatobar.mequi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class ListaEquipoActivity extends AppCompatActivity {

    EditText eId, eNombre, eTelefono;
    TextView eCorreo;
    ListView listView;
    Button bIr;
    String id, nombre, telefono, correo, Dir, Dir2;
    String artefacto="Desfibrilador", marca="asus";
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<ClaseEquipos> list;
    ArrayAdapter adapter;
   // Contactos contactos;
    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipo);

        list = new ArrayList<ClaseEquipos>();
        eNombre = (EditText) findViewById(R.id.lista);
        listView = (ListView) findViewById(R.id.listview);
        bIr = (Button) findViewById(R.id.bBuscar);
        adapter = new ArrayAdapter<ClaseEquipos>(this, android.R.layout.simple_dropdown_item_1line, list);
        listView.setAdapter(adapter);

        myRef = FirebaseDatabase.getInstance().getReference("ClaseEquipos");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(artefacto).exists()){
                    list.add(dataSnapshot.child(artefacto).child(marca).getValue(ClaseEquipos.class));
//                    nombre = info.get(0).getNombre();
//                    sangre = info.get(0).getSangre();
//                    correo = info.get(0).getCorreo();
//                    documento = info.get(0).getDocumento();
//                    tnombre_perfil.setText(nombre);
//                    tsangre_perfil.setText(sangre);
//                    tcorreo_perfil.setText(correo);
//                    tcedula_perfil.setText(documento);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // tLink = (TextView) findViewById(R.id.tId);
       // eNombre = (EditText) findViewById(R.id.eNombre);
    }
}
