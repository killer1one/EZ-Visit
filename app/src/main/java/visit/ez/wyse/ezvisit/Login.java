package visit.ez.wyse.ezvisit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import Sqlite_Data.SQL_Employee;
import Sqlite_Data.SQL_UserMobile;
import Sqlite_Data.Sqlite;
import Sqlite_Data.Sync;


public class Login extends ActionBarActivity {

    String User = "User1";
    String Pass = "Password1";
    String Contrasenña = "prueba";
    public Sqlite myConn;
    public Context _cont;
    public EditText Usuario,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _cont = this;
        myConn = new Sqlite(_cont, null, 1);

        Usuario = (EditText)findViewById(R.id.EditUsuario);
        Password = (EditText)findViewById(R.id.EditContrasena);

       // new mySoapCall().execute("","");

    }

    public void IniciarSesion(View v){
    /*    ArrayList<String> Employee = new ArrayList<String>();
        Employee = SQL_Employee.getEmployee(Usuario.getText().toString(), Password.getText().toString(), myConn);
        if (Employee != null) {

            Intent launch = new Intent(this, ActivityHome.class);
            startActivity(launch);
         } else {
            AlertContrasenñaBad();
           Usuario.setText("");
           Password.setText("");
        }
        */

        // Login THing
        SQL_UserMobile myUser = new SQL_UserMobile(this);

        if(myUser.checkLogin(Usuario.getText().toString(),Password.getText().toString()))
        {
            Usuario.setText("");
            Password.setText("");
            Intent launch = new Intent(this, ActivityHome.class);
            startActivity(launch);
        } else
        {
            Usuario.setText("");
            Password.setText("");
            AlertContrasenñaBad();
        }

    }

    public void IniciarCargaDatos(){
        Intent launch = new Intent(this, Carga_Datos.class);
        startActivity(launch);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {


            case R.id.action_Carga_Datos:
                SetDialogCargaDatos();
                return true;
            case R.id.action_Configuracion:
                return true;
            case R.id.action_About:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public class mySoapCall extends AsyncTask<String, Float, Integer>
    {
        int flagDead = 0;
        @Override
        protected Integer doInBackground(String... p)
        {
            int resulto = 0;

            Sync prueba = new Sync();
            //prueba.getDeliverEdits(User, Pass, 1);

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
        }
    }




    public void SetDialogCargaDatos()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);

        alert.setTitle("Carga de Datos");
        alert.setMessage("Ez-Visit Proceso de Carga Inicial proceda con los pasos para continuar");

        // Set an EditText view to get user input
        final EditText input = new EditText(Login.this);
        input.setHint("Contrasenña");
        alert.setView(input);

        alert.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                if (Contrasenña.equalsIgnoreCase(input.getText().toString())) {
                    IniciarCargaDatos();
                } else {
                    AlertContrasenñaBad();
                }

            }
        });

        alert.show();
    }


    public void AlertContrasenñaBad(){

        AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);

        alert.setTitle("Error");
        alert.setMessage("La Contraseña de Ez-Visit que dígito esta incorrecta Intenta de Nuevo");
        alert.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                SetDialogCargaDatos();
            }
        });

        alert.show();
    }

}
