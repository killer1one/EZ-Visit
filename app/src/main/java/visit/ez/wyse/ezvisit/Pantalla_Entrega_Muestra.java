package visit.ez.wyse.ezvisit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import Sqlite_Data.Data_ProductosTemp;
import Sqlite_Data.SQL_ProductoTemp;
import Sqlite_Data.SQL_Productos;


public class Pantalla_Entrega_Muestra extends ActionBarActivity {

    ListView myLista ;
    Context _Cont;
    public SQL_Productos MyPro;
    public SQL_ProductoTemp MyProTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__entrega__muestra);

        _Cont = this;

        MyPro = new SQL_Productos(_Cont);
        MyProTemp = new SQL_ProductoTemp(_Cont);
        //MyProTemp.DeleteAll();

        myLista = (ListView)findViewById(R.id.LvProducto);
        myLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor Mycur = MyPro.getCursor();
                Mycur.moveToPosition(position);

                SetDialogCantidad(Mycur.getInt(1));


            }
        });

        new myBuscar().execute("", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla__entrega__muestra, menu);
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

    public void SetDialogCantidad(final int ProID)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(Pantalla_Entrega_Muestra.this);

        alert.setTitle("Cantidad De Producto");
        alert.setMessage("La cantidad de Muetra que quieres Dar");

        // Set an EditText view to get user input
        final EditText input = new EditText(Pantalla_Entrega_Muestra.this);
        input.setHint("Digite la cantidad");
        alert.setView(input);

        alert.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                SaveProductosTemp(Integer.valueOf(input.getText().toString()),ProID);
                new myBuscar().execute("", "");
            }
        });

        alert.show();
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
            myLista.setAdapter(MyPro.getProductos(_Cont));
        }
    }



    public void SaveProductosTemp(int Cantidad, int ProID){

        try{

            MyProTemp.DeleteItem(ProID);

            Data_ProductosTemp Temp = new Data_ProductosTemp();
            Temp.Cantidad = Cantidad;
            Temp.ProductID = ProID;

            MyProTemp.saveRecord(Temp);

            Toast.makeText(_Cont, "Se Guardo la cantidad " + Cantidad + " en la entrega", Toast.LENGTH_LONG);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
