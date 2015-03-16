package visit.ez.wyse.ezvisit;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


public class Registrar_Clientes extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_cliente);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar__clientes, menu);
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

    public void GoAnadirDir(View v)
    {
        // Go to Nueva Visita
        Intent launchThing = new Intent(this, AnadirDir.class);
        startActivity(launchThing);
    }

    public void GoAnadirContacto(View v)
    {
        // Go to Nueva Visita
        Intent launchThing = new Intent(this, AnadirContacto.class);
        startActivity(launchThing);
    }

    public void CancelarCliente(View v)
    {
        finish();
    }

    public void SalvarCliente(View v)
    {
        // Stuff
        Spinner spTipoCliente = (Spinner)findViewById(R.id.spinTipoCliente);
        EditText editNombre = (EditText)findViewById(R.id.editNombre);
        EditText editApellido = (EditText)findViewById(R.id.editApellido);

        Spinner spEspec = (Spinner)findViewById(R.id.spinEspec);
        Spinner spClasificacion = (Spinner)findViewById(R.id.spinClasificacion);
    }
}
