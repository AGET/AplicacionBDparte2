package aget.aget.workstation.aplicacionbdparte2;

/* Codigo*/

import android.content.ContentValues;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Insertar extends Activity {

    EditText id, nom, tel, mail, pais;
    Button guardar;
    SQLHelper sqlhelper;
    SQLiteDatabase db;
    final String BASEDEDATOS = "BD_Agenda.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        id = (EditText) findViewById(R.id.id);
        nom = (EditText) findViewById(R.id.nombre);
        tel = (EditText) findViewById(R.id.telefono);
        mail = (EditText) findViewById(R.id.correo);
        guardar = (Button) findViewById(R.id.guardar);
        pais = (EditText) findViewById(R.id.pais);

        Bundle parametros = getIntent().getExtras();
        id.setText(parametros.getString("id"));
        nom.setText(parametros.getString("nom"));
        tel.setText(parametros.getString("tel"));
        mail.setText(parametros.getString("mail"));
        pais.setText(parametros.getString("pais"));
        id.setSelected(false);

        guardar.setText(parametros.getString("boton"));

        sqlhelper = new SQLHelper (this);
        //sqlhelper = new SQLHelper(this, BASEDEDATOS, null, 1);

        guardar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                db = sqlhelper.getWritableDatabase();
                // TODO Auto-generated method stub
                if (guardar.getText().equals("Insertar")){
                   /* db.execSQL(" INSERT INTO contacto "
                            +" (id,nombre,telefono, correo,pais) "
                            +" VALUES ( "
                            +" '"+id.getText().toString()+"', "
                            +" '"+nom.getText().toString()+"', "
                            +" '"+tel.getText().toString()+"', "
                            +" '"+mail.getText().toString()+"', "
                            +" '"+pais.getText().toString()+"' "
                            +" ) ");*/

                    db = sqlhelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put("nombre", nom.getText().toString());
                    values.put("telefono", tel.getText().toString());
                    values.put("correo", mail.getText().toString());
                    values.put("pais", pais.getText().toString());

                    db.insert("contacto", null, values);
                    db.close();


                }
                //db.close();
               finish();
            }
        });

    }


}