package visit.ez.wyse.ezvisit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import Sqlite_Data.Data_PlanTrabajo;
import Sqlite_Data.SQL_ClientTipos;
import Sqlite_Data.SQL_ClienteMaestro;
import Sqlite_Data.SQL_Employee;
import Sqlite_Data.SQL_PlanTrabajo;


public class Registrar_Visita extends ActionBarActivity {

    Spinner spTipoCliente;
    public SQL_ClientTipos CliTipo;
    public SQL_PlanTrabajo  MyPlan;
    public Context _Con;
    public static TextView labelCliente;
    public static int ClientID;
    public CalendarView cv;
    public CheckBox checkProgramada;

    EditText CliBuscar;
    ListView myLista ;
    SQL_ClienteMaestro myCLiMaes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_visita);
        _Con = this;

        CliTipo = new SQL_ClientTipos(_Con);
        MyPlan = new SQL_PlanTrabajo(_Con);

        // Stuff
        spTipoCliente = (Spinner)findViewById(R.id.spinTipo);
        spTipoCliente.setAdapter(CliTipo.ListaClienteTipo(_Con));

        labelCliente = (TextView)findViewById(R.id.labelCliente);
        checkProgramada = (CheckBox)findViewById(R.id.checkProgramada);

         cv = (CalendarView)findViewById(R.id.calendarView);

        CliBuscar = (EditText)findViewById(R.id.editBuscar);
        myLista = (ListView)findViewById(R.id.listClientes);

        myCLiMaes = new SQL_ClienteMaestro(_Con);

        myLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor Mycli = myCLiMaes.getCliente();
                Mycli.moveToPosition(position);

                Registrar_Visita.labelCliente.setText("[" + Mycli.getString(1) + " - " + Mycli.getString(2) + "]");
                Registrar_Visita.ClientID = Mycli.getInt(3);


                Toast.makeText(_Con, "As Seleccionado el Cliente " + Mycli.getString(1), Toast.LENGTH_LONG);

                finish();


            }
        });

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


    public void CancelarVisita(View v)
    {
        // Cancelar la operacion;
        finish();
    }

    public void SalvarVisita(View v)
    {
        try {
            Data_PlanTrabajo DMyPlan = new Data_PlanTrabajo();
            SQL_Employee MyEmp = new SQL_Employee(_Con);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String selectedDate = sdf.format(new Date(cv.getDate()));


            DMyPlan.MasterID = 0;
            DMyPlan.EmployeeID = MyEmp.getEmployeeID();
            DMyPlan.ClientID = ClientID;
            DMyPlan.PTHora = getHoraActual();
            DMyPlan.PTFecha =selectedDate;
            DMyPlan.PTEstado = 0;
            DMyPlan.PTTipo = CliTipo.getCLienteTipo(spTipoCliente.getSelectedItem().toString());
            DMyPlan.PTPRog = 0;
            DMyPlan.SuperID = 0;
            DMyPlan.VisitID = MyPlan.GetVisitID() +1;

            MyPlan.saveRecord(DMyPlan);

            AlertSave();
        }catch (Exception e ){
            e.printStackTrace();
        }

    }


    public String getFechaActual() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = formatter.format(new Date());
        return fecha;
    }

    public String getHoraActual() {
        Format formatter = new SimpleDateFormat("HH:mm:ss");
        String fecha = formatter.format(new Date());
        return fecha;
    }

    public void AlertSave(){

        AlertDialog.Builder alert = new AlertDialog.Builder(Registrar_Visita.this);

        alert.setTitle("Mensaje");
        alert.setMessage("El Registro de Visita Se Guardo Correctamente");
        alert.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        alert.show();
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
}
