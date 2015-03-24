package visit.ez.wyse.ezvisit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import Sqlite_Data.Data_ContactoTemp;
import Sqlite_Data.SQL_ClientTipos;
import Sqlite_Data.SQL_ContactoTemp;
import Sqlite_Data.SQL_ContactoTipos;


public class AnadirContacto extends ActionBarActivity {

    public SQL_ContactoTemp SqlConTemp;
    public Context _Con;
    public SQL_ContactoTipos myCT;
    EditText editTelefono;
    EditText editCorreo;
    ListView myList;
    Spinner spTipoContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_contacto);
        _Con = this;

        SqlConTemp = new SQL_ContactoTemp(_Con);

        myCT = new SQL_ContactoTipos(this);

        spTipoContacto = (Spinner)findViewById(R.id.spinTipoContacto);
        spTipoContacto.setAdapter(myCT.ListaTipos(_Con));

        spTipoContacto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String TipoDeContacto = ((TextView)view).getText().toString();

                if(TipoDeContacto.equals("Correo Electronico") || TipoDeContacto.equals("Correo Electrónico") || TipoDeContacto.equals("Email")  )
                {
                    TextView myT = (TextView)findViewById(R.id.textViewTelefono);
                    myT.setVisibility(View.INVISIBLE);

                    EditText myE = (EditText)findViewById(R.id.editTel);
                    myE.setVisibility(View.INVISIBLE);

                    myT = (TextView)findViewById(R.id.textViewCorreo);
                    myT.setVisibility(View.VISIBLE);

                    myE = (EditText)findViewById(R.id.editMail);
                    myE.setVisibility(View.VISIBLE);
                } else if (TipoDeContacto.equals("Telefono Fijo") || TipoDeContacto.equals("Teléfono Fijo")  )
                {
                    TextView myT = (TextView)findViewById(R.id.textViewTelefono);
                    myT.setVisibility(View.VISIBLE);

                    EditText myE = (EditText)findViewById(R.id.editTel);
                    myE.setVisibility(View.VISIBLE);

                    myT = (TextView)findViewById(R.id.textViewCorreo);
                    myT.setVisibility(View.INVISIBLE);

                    myE = (EditText)findViewById(R.id.editMail);
                    myE.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        myList = (ListView)findViewById(R.id.listViewContactos);
        myList.setAdapter(SqlConTemp.ShowConTemp(_Con));

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
        myConTemp.DirTipo = myCT.getTipoID(spTipoContacto.getSelectedItem().toString());;
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
