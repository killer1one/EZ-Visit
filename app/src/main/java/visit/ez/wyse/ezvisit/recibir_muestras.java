package visit.ez.wyse.ezvisit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import Sqlite_Data.SQL_Inventario;
import Sqlite_Data.SQL_ProductoLoteD;
import Sqlite_Data.SQL_ProductoLoteM;


public class recibir_muestras extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibir_muestras);

        // Getting the query and putting it on the listview
        SQL_ProductoLoteD myLote = new SQL_ProductoLoteD(this);

        ListView listLote = (ListView)findViewById(R.id.listLote);

        listLote.setAdapter(myLote.GetListaLote(this));

        myLote.close();

        //Tomando el ID para el lote
        SQL_ProductoLoteM myLoteM = new SQL_ProductoLoteM(this);

        TextView textLote = (TextView)findViewById(R.id.label_lote);

        textLote.setText(String.valueOf(myLoteM.getLoteID()));

        myLoteM.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recibir_muestras, menu);
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

    public void CancelarTodo(View v)
    {
        finish();
    }

    public void AprobarLote(View v)
    {
        SQL_ProductoLoteM myLoteM = new SQL_ProductoLoteM(this);

        myLoteM.AprobarLote();

        SQL_ProductoLoteD myPL = new SQL_ProductoLoteD(this);

        // Hay que añadir la creación de inventario aqui.
        SQL_Inventario myI = new SQL_Inventario(this);

        myI.TransferProducts(myPL.getCursor());

        myLoteM.close();

        myI.close();

        finish();
    }

    public void RechazarLote(View v)
    {
        SQL_ProductoLoteM myLoteM = new SQL_ProductoLoteM(this);

        myLoteM.RechazarLote();

        myLoteM.close();

        finish();
    }


}
