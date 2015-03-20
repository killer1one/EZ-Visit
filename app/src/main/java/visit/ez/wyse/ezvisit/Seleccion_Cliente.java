package visit.ez.wyse.ezvisit;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import Sqlite_Data.SQL_ClienteMaestro;


public class Seleccion_Cliente extends ActionBarActivity {

    EditText CliBuscar;
    ListView myLista ;
    SQL_ClienteMaestro myCLiMaes;
    Context _Con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_cliente);
        _Con = this;

        CliBuscar = (EditText)findViewById(R.id.editBuscar);
        myLista = (ListView)findViewById(R.id.listClientes);

        myCLiMaes = new SQL_ClienteMaestro(_Con);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccion__cliente, menu);
        return true;
    }


    public void Buscar(View v){

        new myBuscar().execute("","");
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
            myLista.setAdapter(myCLiMaes.getCliente(_Con,CliBuscar.getText().toString()));
        }
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
}
