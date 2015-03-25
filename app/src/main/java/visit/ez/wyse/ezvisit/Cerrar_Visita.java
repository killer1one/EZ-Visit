package visit.ez.wyse.ezvisit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import Sqlite_Data.Data_HistorialDes;
import Sqlite_Data.SQL_ClientTipos;
import Sqlite_Data.SQL_Employee;
import Sqlite_Data.SQL_HistorialDes;
import Sqlite_Data.SQL_PlanTrabajo;
import Sqlite_Data.SQL_ProductoTemp;


public class Cerrar_Visita extends ActionBarActivity {

    public TextView TVNombre, TVApellido, TVObjetivoVA, TVComentarioVA, TVDirecion;
    public Spinner spTipoCliente, spinEstado;
    public SQL_ClientTipos CliTipo;
    public Context _Con;
    public CalendarView Cv;
    public EditText editObjetivos, editComentarios;

    public static int CLientID, VisitID, RepID;
    public static String Nombre, Apellido, Dirrecion;
    public SQL_HistorialDes HisDesSql;
    public SQL_PlanTrabajo MyPlan;
    public SQL_ProductoTemp MyProTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cerrar_visita);
        _Con = this;
        HisDesSql = new SQL_HistorialDes(_Con);
        MyPlan = new SQL_PlanTrabajo(_Con);
        MyProTemp = new SQL_ProductoTemp(_Con);

        CliTipo = new SQL_ClientTipos(_Con);
        TVNombre = (TextView)findViewById(R.id.labelNombre);
        TVApellido = (TextView)findViewById(R.id.labelApellido);
        TVObjetivoVA = (TextView)findViewById(R.id.labelObjetivo);
        TVComentarioVA = (TextView)findViewById(R.id.labelComentario);
        TVDirecion = (TextView)findViewById(R.id.labelDireccion);
        Cv = (CalendarView)findViewById(R.id.calendarView2);
        editObjetivos = (EditText)findViewById(R.id.editObjetivos);
        editComentarios = (EditText)findViewById(R.id.editComentarios);

        // Stuff
        spTipoCliente = (Spinner)findViewById(R.id.spinTipoVisita);
        spTipoCliente.setAdapter(CliTipo.ListaClienteTipo(_Con));
        spinEstado = (Spinner)findViewById(R.id.spinEstado);
        spinEstado.setAdapter(CliTipo.getEstadoVisita(_Con));


        TVNombre.setText("[" + Nombre + "]");
        TVApellido.setText("["+Apellido+"]");
        TVDirecion.setText("["+Dirrecion+"]");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro__gastos, menu);
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

    public void Guardar(View v){

        try{
            Data_HistorialDes HisDes = new Data_HistorialDes();
            SQL_Employee MyEmp = new SQL_Employee(_Con);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String selectedDate = sdf.format(new Date(Cv.getDate()));

            int Estado = 0;

            if(spinEstado.getSelectedItem().toString().equalsIgnoreCase("Completa")){
                Estado = 1;
            }else if(spinEstado.getSelectedItem().toString().equalsIgnoreCase("Pospuesta")) {
                Estado = 0;
            }else if(spinEstado.getSelectedItem().toString().equalsIgnoreCase("Fallida")) {
                Estado = -1;
            }

            HisDes.EmployeeID = MyEmp.getEmployeeID();
            HisDes.HDEstado = Estado;
            HisDes.HDFecha = selectedDate;
            HisDes.HDHora = getHoraActual();
            HisDes.HDNotas = editComentarios.getText().toString();
            HisDes.HDObjetivo = editObjetivos.getText().toString();
            HisDes.VisitID = VisitID;
            HisDes.MasterID = 0;
            HisDes.RegID = RepID;

            HisDesSql.saveRecord(HisDes);

            MyPlan.ActualizarEstatus(RepID);

            AlertSave();

            MyProTemp.DeleteAll();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void AlertSave(){

        AlertDialog.Builder alert = new AlertDialog.Builder(Cerrar_Visita.this);

        alert.setTitle("Mensaje");
        alert.setMessage("La Visita Se Guardo Correctamente");
        alert.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        alert.show();
    }

    public String getHoraActual() {
        Format formatter = new SimpleDateFormat("HH:mm:ss");
        String fecha = formatter.format(new Date());
        return fecha;
    }

    public void Cancelar(View v){
        finish();
    }
    public void GoEntrega(View v){
        Intent launchThing = new Intent(this, Pantalla_Entrega_Muestra.class);
        startActivity(launchThing);
    }


}
