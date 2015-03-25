package visit.ez.wyse.ezvisit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import Sqlite_Data.Data_ClientAddress;
import Sqlite_Data.Data_ClienteContacto;
import Sqlite_Data.Data_ClienteDatosZ;
import Sqlite_Data.Data_ClienteMaestro;
import Sqlite_Data.SQL_ClientAddress;
import Sqlite_Data.SQL_ClientTipos;
import Sqlite_Data.SQL_ClienteContacto;
import Sqlite_Data.SQL_ClienteDatosZ;
import Sqlite_Data.SQL_ClienteMaestro;
import Sqlite_Data.SQL_ContactoTemp;
import Sqlite_Data.SQL_DirrecionTemp;
import Sqlite_Data.SQL_Especialidades;


public class Registrar_Clientes extends ActionBarActivity {

    public SQL_ClientTipos CliTipo;
    public SQL_Especialidades Espec;
    public SQL_ClienteMaestro CliMaes;
    public SQL_ClienteDatosZ CliDatoZ;
    public SQL_DirrecionTemp DirTemp;
    public SQL_ContactoTemp ConTemp;

    public SQL_ClientAddress CLiDir;
    public SQL_ClienteContacto CLiCon;


    public Context _Con;
    EditText editNombre, editApellido;
    Spinner spClasificacion,spTipoCliente, spEspec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_cliente);
        _Con = this;

        CliTipo = new SQL_ClientTipos(_Con);
        CliMaes = new SQL_ClienteMaestro(_Con);
        //Temp
        DirTemp = new SQL_DirrecionTemp(_Con);
        ConTemp = new SQL_ContactoTemp(_Con);
        CliDatoZ = new SQL_ClienteDatosZ(_Con);
        CLiDir = new SQL_ClientAddress(_Con);
        CLiCon = new SQL_ClienteContacto(_Con);

        Espec =  new SQL_Especialidades(_Con);

        // Stuff
        spTipoCliente = (Spinner)findViewById(R.id.spinTipoCliente);
        spTipoCliente.setAdapter(CliTipo.ListaClienteTipo(_Con));

        spTipoCliente.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String TipoDeCliente = ((TextView)view).getText().toString();
                if(TipoDeCliente.equals("Centro Medico") || TipoDeCliente.equals("Centro Médico") )
                {
                    TextView myT = (TextView)findViewById(R.id.textViewNombreCliente);
                    myT.setText("Nombre del Centro");
                    myT = (TextView)findViewById(R.id.textViewApellidoCliente);
                    myT.setVisibility(View.INVISIBLE);

                    EditText myE = (EditText)findViewById(R.id.editApellido);
                    myE.setVisibility(View.INVISIBLE);

                    Spinner myS = (Spinner)findViewById(R.id.spinEspec);
                    myS.setVisibility(View.INVISIBLE);
                } else if(TipoDeCliente.equals("Medico") || TipoDeCliente.equals("Médico"))
                {
                    TextView myT = (TextView)findViewById(R.id.textViewNombreCliente);
                    myT.setText("Nombre del Médico");
                    myT = (TextView)findViewById(R.id.textViewApellidoCliente);
                    myT.setText("Apellido del Médico");
                    myT.setVisibility(View.VISIBLE);

                    EditText myE = (EditText)findViewById(R.id.editApellido);
                    myE.setVisibility(View.VISIBLE);

                    Spinner myS = (Spinner)findViewById(R.id.spinEspec);
                    myS.setVisibility(View.VISIBLE);

                } else if(TipoDeCliente.equals("(Seleccione)"))
                {
                    TextView myT = (TextView)findViewById(R.id.textViewNombreCliente);
                    myT.setText("Nombre del Cliente");
                    myT = (TextView)findViewById(R.id.textViewApellidoCliente);
                    myT.setText("Apellido del Cliente");
                    myT.setVisibility(View.VISIBLE);

                    EditText myE = (EditText)findViewById(R.id.editApellido);
                    myE.setVisibility(View.VISIBLE);

                    Spinner myS = (Spinner)findViewById(R.id.spinEspec);
                    myS.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spEspec = (Spinner)findViewById(R.id.spinEspec);
        spEspec.setAdapter(Espec.ListaEspecialidades(_Con));

        spClasificacion = (Spinner)findViewById(R.id.spinClasificacion);
        spClasificacion.setAdapter(CliTipo.ListaClasificacion(_Con));

        //Spinner spEspec = (Spinner)findViewById(R.id.spinEspec);

        editNombre = (EditText)findViewById(R.id.editNombre);
        editApellido = (EditText)findViewById(R.id.editApellido);


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

        try{

            /* GUARDANDP CLIENTE CONCTATO
            Getting the temporal detail list*/

            ConTemp.getAllData(this);

            Cursor TempListCon = ConTemp.getCursor();
            Data_ClienteContacto DetalleConcta = new Data_ClienteContacto();

            if (TempListCon.moveToFirst())
            {
                //Recorremos el cursor hasta que no haya m�s registros
                do {
                    //DirTipo, Telefono, Correo

                    DetalleConcta.ClientCorreo = TempListCon.getString(2);
                    DetalleConcta.NumeroTel = TempListCon.getString(1);
                    DetalleConcta.TipoContacto = TempListCon.getInt(0);

                    CLiCon.saveRecord(DetalleConcta);


                } while(TempListCon.moveToNext());

            } // if ends here

            ConTemp.getDeleteAll();


            /* GUARDANDP CLIENTE ADDRESS
            Getting the temporal detail list*/
            DirTemp.getAllData(this);

            Cursor TempListDir = DirTemp.getCursor();
            Data_ClientAddress DetalleAdres = new Data_ClientAddress();

            if (TempListDir.moveToFirst())
            {
                //Recorremos el cursor hasta que no haya mas registros
                do {
                    //DirTipo, Direccion

                    DetalleAdres.ClientDireccion = TempListDir.getString(1);
                    DetalleAdres.TipoAddress = TempListDir.getInt(0);
                    DetalleAdres.ClientLatitud = "";
                    DetalleAdres.ClientLongitud = "";
                    DetalleAdres.MasterID = 0;
                    DetalleAdres.ClientAddressID = CLiDir.GetLastID() + 1;

                    CLiDir.saveRecord(DetalleAdres);

                } while(TempListDir.moveToNext());

            } // if ends here

            DirTemp.getDeleteAll();


            /*  GUARDANDP CLIENTE
            Getting the temporal detail list*/

            Data_ClienteMaestro mycli = new Data_ClienteMaestro();

            mycli.Clasificacion = spClasificacion.getSelectedItem().toString();
            mycli.ClientAddressID = Integer.valueOf(CLiDir.GetLastID());
            mycli.ClientApellido = editApellido.getText().toString();
            mycli.ClientCode = "C"+Integer.valueOf(CLiDir.GetLastID());
            mycli.ClientContactID = Integer.valueOf(CLiCon.GetLastID());
            //mycli.ClientID = 0;
            mycli.ClientNombre = editNombre.getText().toString();
            mycli.ClientTipo = CliTipo.getCLienteTipo(spTipoCliente.getSelectedItem().toString());
            mycli.MasterID = 0;

            CliMaes.saveRecord(mycli);


             /*  GUARDANDP CLIENTE DatosZ
            Getting the temporal detail list*/
            Data_ClienteDatosZ mycliDatoZ = new Data_ClienteDatosZ();
            mycliDatoZ.Clasificacion = spClasificacion.getSelectedItem().toString();
            mycliDatoZ.ClientID = Integer.valueOf(CliMaes.GetLastID());
            mycliDatoZ.EspecID = Espec.getEspecialidad(spEspec.getSelectedItem().toString());
            mycliDatoZ.MasterID = 0;
            mycliDatoZ.RegID = 0;
            mycliDatoZ.ZonaID = 0;

            CliDatoZ.saveRecord(mycliDatoZ);


            //Actualizando el Cliente ID EN los Detalle
            CLiDir.ActualizarCLienteID(CliMaes.GetLastID(), CLiDir.GetLastID());
            CLiCon.ActualizarCLienteID(CliMaes.GetLastID(),CLiCon.GetLastID() );



        }catch (Exception e){
            e.printStackTrace();
        }

        AlertSave();

    }


    public void AlertSave(){

        AlertDialog.Builder alert = new AlertDialog.Builder(Registrar_Clientes.this);

        alert.setTitle("Mensaje");
        alert.setMessage("El cliente Se Guardo Correctamente");
        alert.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        alert.show();
    }
}
