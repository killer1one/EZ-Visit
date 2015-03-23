package visit.ez.wyse.ezvisit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import Sqlite_Data.SQL_ClientTipos;


public class Cerrar_Visita extends ActionBarActivity {

    public TextView TVNombre, TVApellido, TVObjetivoVA, TVComentarioVA, TVDirecion;
    public Spinner spTipoCliente, spinEstado;
    public SQL_ClientTipos CliTipo;
    public Context _Con;
    public CalendarView Cv;
    public EditText editObjetivos, editComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cerrar_visita);
        _Con = this;

        CliTipo = new SQL_ClientTipos(_Con);
        TVNombre = (TextView)findViewById(R.id.labelNombre);
        TVApellido = (TextView)findViewById(R.id.labelApellido);
        TVObjetivoVA = (TextView)findViewById(R.id.labelObjetivo);
        TVComentarioVA = (TextView)findViewById(R.id.labelComentario);
        TVDirecion = (TextView)findViewById(R.id.labelDireccion);
        Cv = (CalendarView)findViewById(R.id.calendarView2);
        editObjetivos = (EditText)findViewById(R.id.editObjetivos);
        editComentarios = (EditText)findViewById(R.id.editComentarios);

        // Stuff
        spTipoCliente = (Spinner)findViewById(R.id.spinTipoVisita);
        spinEstado = (Spinner)findViewById(R.id.spinEstado);
        spTipoCliente.setAdapter(CliTipo.ListaClienteTipo(_Con));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro__gastos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Guardar(View v){

    }

    public void Cancelar(View v){

    }
    public void GoEntrega(View v){
        Intent launchThing = new Intent(this, Pantalla_Entrega_Muestra.class);
        startActivity(launchThing);
    }


}
