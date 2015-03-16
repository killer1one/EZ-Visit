package visit.ez.wyse.ezvisit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import Sqlite_Data.Data_ContactoTemp;
import Sqlite_Data.SQL_ClientTipos;
import Sqlite_Data.SQL_ContactoTemp;


public class AnadirContacto extends ActionBarActivity {

    public SQL_ClientTipos CliTipo;
    public SQL_ContactoTemp SqlConTemp;
    public Context _Con;
    EditText editTelefono;
    EditText editCorreo;
    ListView myList;
    Spinner spTipoContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_contacto);
        _Con = this;

        CliTipo = new SQL_ClientTipos(_Con);
        SqlConTemp = new SQL_ContactoTemp(_Con);

         spTipoContacto = (Spinner)findViewById(R.id.spinTipoContacto);
        spTipoContacto.setAdapter(CliTipo.ListaClienteTipo(_Con));

        myList = (ListView)findViewById(R.id.listViewContactos);

         editTelefono = (EditText)findViewById(R.id.editTel);
         editCorreo = (EditText)findViewById(R.id.editMail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_anadir_contacto, menu);
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

    public void AddContacto(View v)
    {
        // Getting the objects
        Data_ContactoTemp myConTemp = new Data_ContactoTemp();
        myConTemp.Correo = editCorreo.getText().toString();
        myConTemp.DirTipo = CliTipo.getCLienteTipo(spTipoContacto.getSelectedItem().toString());;
        myConTemp.Telefono = editTelefono.getText().toString();

        SqlConTemp.saveRecord(myConTemp);

        myList.setAdapter(SqlConTemp.ShowConTemp(_Con));


        // Transferir los valores a la lista
    }

    public void CancelarContacto(View v)
    {
        finish();
    }

    public void SalvarContacto(View v)
    {
        finish();
    }
}
