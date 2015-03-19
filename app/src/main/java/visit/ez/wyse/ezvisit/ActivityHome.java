package visit.ez.wyse.ezvisit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ActivityHome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {


            case R.id.action_entrega_muestra:
                Intent launch = new Intent(this, Entrega_Muestra.class);
                startActivity(launch);
                return true;
            case R.id.action_operativos:
                Intent launch1 = new Intent(this, Operativo_Medico.class);
                startActivity(launch1);
                return true;
            case R.id.action_gastos:
                Intent launch2 = new Intent(this, Registro_Gastos.class);
                startActivity(launch2);
                return true;
            case R.id.action_Asignacion_ruta:
                Intent launch3 = new Intent(this, Asignacion_Ruta.class);
                startActivity(launch3);
                return true;
            case R.id.action_presupuesto:
                Intent launch4 = new Intent(this, Presupuesto.class);
                startActivity(launch4);
                return true;
            case R.id.action_Consulta_Transaciones:
                Intent launc5 = new Intent(this, Consulta_Transaciones.class);
                startActivity(launc5);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void GoPlanTrabajo(View v)
    {
        // Go to Plan Trabajo
        Intent launchThing = new Intent(this, Plan_Trabajo.class);
        startActivity(launchThing);
    }

    public void GoRegistroClientes(View v)
    {
        // Go to Registro Clientes
        Intent launchThing = new Intent(this, Registrar_Clientes.class);
        startActivity(launchThing);
    }

    public void GoMuestras(View v)
    {
        // Go to Plan Trabajo
        Intent launchThing = new Intent(this, recibir_muestras.class);
        startActivity(launchThing);
    }
}
