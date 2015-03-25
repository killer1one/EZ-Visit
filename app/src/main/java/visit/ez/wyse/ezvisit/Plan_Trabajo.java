package visit.ez.wyse.ezvisit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import Sqlite_Data.SQL_PlanTrabajo;


public class Plan_Trabajo extends ActionBarActivity {

    ListView myLista ;
    SQL_PlanTrabajo MyPlan;
    Context _Cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_trabajo);
        _Cont = this;

        MyPlan = new SQL_PlanTrabajo(_Cont);

        myLista = (ListView)findViewById(R.id.listVisitas);

        myLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor MyPlant = MyPlan.getPlanT();
                MyPlant.moveToPosition(position);

                Cerrar_Visita.Apellido = MyPlant.getString(4);
                Cerrar_Visita.Nombre = MyPlant.getString(1);
                Cerrar_Visita.Dirrecion = MyPlant.getString(2);
                Cerrar_Visita.RepID = MyPlant.getInt(5);
                Cerrar_Visita.VisitID = MyPlant.getInt(6);

                GoCerrarVisita();
            }
        });

        new myBuscar().execute("","");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan__trabajo, menu);
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

    public void GoNuevaVisita(View v)
    {
        // Go to Nueva Visita
        Intent launchThing = new Intent(this, Registrar_Visita.class);
        startActivity(launchThing);
    }

    public void GoCerrarVisita()
    {
        // Go to Nueva Visita
        Intent launchThing = new Intent(this, Cerrar_Visita.class);
        startActivity(launchThing);
    }


    @Override
    public void onResume(){

        new myBuscar().execute("","");

        super.onResume();
    }


    public class myBuscar extends AsyncTask<String, Float, Integer>
    {
        int flagDead = 0;
        @Override
        protected Integer doInBackground(String... p)
        {
            int resulto = 0;

            return resulto;
        }

        // Before Execution
        protected void onPreExecute()
        {
        }

        // When progress is made
        protected void onProgressUpdate (Float... values)
        {
        }

        // After the execution
        protected void onPostExecute(Integer result) {
            myLista.setAdapter(MyPlan.getPlanTrabajo(_Cont));
        }
    }
}
