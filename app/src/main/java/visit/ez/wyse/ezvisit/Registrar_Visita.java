package visit.ez.wyse.ezvisit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import Sqlite_Data.SQL_ClientTipos;


public class Registrar_Visita extends ActionBarActivity {

    Spinner spTipoCliente;
    public SQL_ClientTipos CliTipo;
    public Context _Con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_visita);
        _Con = this;

        CliTipo = new SQL_ClientTipos(_Con);

        // Stuff
        spTipoCliente = (Spinner)findViewById(R.id.spinTipo);
        spTipoCliente.setAdapter(CliTipo.ListaClienteTipo(_Con));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar__visita, menu);
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

    public void BuscarCliente(View v)
    {
        // Go to Nueva Visita
        Intent launchThing = new Intent(this, Seleccion_Cliente.class);
        startActivity(launchThing);
    }

    public void CancelarVisita(View v)
    {
        // Cancelar la operacion;
        finish();
    }

    public void SalvarVisita(View v)
    {
        // Guardar la operacion;
    }
}
