package aget.aget.workstation.aplicacionbdparte2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ListView lstv;

    SQLHelper sqlhelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstv = (ListView) findViewById(R.id.lista);

        actualiza();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


/* Codigo */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.insertar:
                Intent intent = new Intent(getApplicationContext(),
                        Insertar.class);
                intent.putExtra("id","");
                intent.putExtra("nom","");
                intent.putExtra("tel","");
                intent.putExtra("mail","");
                intent.putExtra("pais","");
                intent.putExtra("boton","Insertar");
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            System.out.println("ACTUALIZA");
            actualiza();
        }
    }

    protected void actualiza(){
        sqlhelper = new SQLHelper (this);
        db = sqlhelper.getWritableDatabase();

        Cursor c = db.rawQuery(
                "select "
                        + "c._id, "
                        + "c.nombre, "
                        + "c.telefono, "
                        + "c.correo, "
                        + "p.nombre "
                        + " from contacto c "
                        + " inner join pais p "
                        + " on c.pais=p.id",null);

//		Cursor c = db.rawQuery("select c.id, c.nombre, c.telefono, c.correo, c.pais from contacto c",null);

        if (c.moveToFirst()){
            ArrayList<String> arreglo =
                    new ArrayList<String>(c.getCount());
            do{
                String id = c.getString(0);
                String nom = c.getString(1);
                String tel = c.getString(2);
                String mail = c.getString(3);
                String pais = c.getString(4);
                arreglo.add(id+"-"+nom+"-"+tel+"-"+mail+"-"+pais);
            }while(c.moveToNext());
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            arreglo);
            lstv.setAdapter(adapter);
        }else{
            ArrayList<String> arreglo = new ArrayList<String>(1);
            arreglo.add("Sin Datos");
            ArrayAdapter<String> adapter =

                    new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            arreglo
                    );
            lstv.setAdapter(adapter);
        } // if

        db.close();
    }
}
