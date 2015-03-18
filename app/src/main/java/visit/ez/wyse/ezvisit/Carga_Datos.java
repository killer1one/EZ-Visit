package visit.ez.wyse.ezvisit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import Sqlite_Data.SQL_Employee;
import Sqlite_Data.Sqlite;
import Sqlite_Data.Sync;


public class Carga_Datos extends Activity {


    public EditText Usuario,Password;
    public int screenOrient;
    private Sqlite myConn;
    boolean endSync = false;
    ProgressDialog dialogProcess;
    public Context _cont;
    public SQLiteDatabase db;
    public float myProcesosBarra = 0;
    public float mySignalBarra = 0;
    public int p = 0;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga__datos);

        _cont = this;
        btnLogin = (Button) findViewById(R.id.btn_Inicial_Carga);
        myConn = new Sqlite(_cont, null, 1);
        db = myConn.getWritableDatabase();


        Usuario = (EditText)findViewById(R.id.EditUsuario);
        Password = (EditText)findViewById(R.id.EditContrasena);



    }


    public void CargaDatos(View v){


        // get Internet status Para ver si Tiene Internet
       // isInternetPresent = cd.isConnectingToInternet();
        //if (isInternetPresent) {

            dialogProcess = new ProgressDialog(this);
            dialogProcess.setMessage("Procesando solicitud Carga de Datos  favor espere.");
            dialogProcess.setTitle("Progreso EZ-Visit");
            dialogProcess.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialogProcess.setCancelable(false);
            dialogProcess.setProgress(0);
            dialogProcess.setMax(100);
            dialogProcess.show();

            // Creating a Thread for this lenghty task
            new Thread(new Runnable() {
                public void run() {
                    try {
						/*
						 * //Bloqueando la pantalla y poniendo que no se apage
						 * la pantalla
						 */

                        //Variable de Progreso
                        myProcesosBarra = 0;
                        mySignalBarra = 0;
                        p = 0;


                        //Eliminando Los registos
                        myConn.onUpgrade(db,1,1);

                        Sync sync = new Sync();

                        String User = Usuario.getText().toString();
                        String Clave = Password.getText().toString();

                        // poniendo que entre a reportar la barra
                        sync.setActivityCargaDatos(Carga_Datos.this);

                        //Este metodo te devuelve la cantidad de registros
                        //sync.getIntegrityFull("","","",_cont);

                        //Creando Variable para determinar si vale la pena continuar el proceso
                        int flagResult = 0;
                        /*
                        flagResult va a ser igual a una variedad de numeros negativos
                        si algo malo sucedio. Si todo sale bien se mantendra sobre 0
                        Las condicionales de mas adelante estan para prevenir que se guarden
                        respuestas que son basicamente codigos de error en la base de datos
                         */

                        //Obteniendo Empleado
                        if(flagResult>=0) {
                            flagResult = sync.getEmployee(User, Clave, 0, _cont);
                        }

                        //Obteniendo datos del usuario especifico
                        if(flagResult>=0) {
                            flagResult = sync.getUserMobile(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(1);
                        }

                        if(flagResult>=0) {
                            //Obteniendo ClienteMaestro
                            sync.getClienteMaestro(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(2);
                        }

                        if(flagResult>=0) {
                            //Obteniendo ClienteContacto
                            sync.getClienteContacto(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(3);
                        }

                        if(flagResult>=0) {
                            //Obteniendo ClienteMaestro
                            sync.getClientAddress(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(4);
                        }

                        if(flagResult>=0) {
                            //Obteniendo ClientAsignados
                            sync.getClientAsignados(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(5);
                        }

                        if(flagResult>=0) {
                            //Obteniendo ClientTipos
                            sync.getClientTipos(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(6);
                        }

                        if(flagResult>=0) {
                            //Obteniendo Zonas
                            sync.getZonas(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(7);
                        }

                        if(flagResult>=0) {
                            sync.getContactoTipos(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(8);
                        }

                        if(flagResult>=0) {
                            sync.getEmployeeTipos(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(9);
                        }

                        if(flagResult>=0) {
                            sync.getPTTipos(User, Clave, 0, _cont);
                        }

                        if(flagResult>=0) {
                            //Reportando Progreso
                            ReportarProgreso(10);
                        }

                        if(flagResult>=0) {
                            // Creando usuario para login regular
                            sync.getEspecialidades(User, Clave, 0, _cont);
                        }

                        endSync = true;

                        progressHandler.sendMessage(progressHandler.obtainMessage());
                        Thread.sleep(3000);
                        endSync = false;
                        dialogProcess.dismiss();

                        ArrayList<String> Employee = new ArrayList<String>();

                        Employee = SQL_Employee.getEmployee(User,Clave, myConn );

                        if (Employee != null) {

                            dialogProcess.dismiss();
                            startActivity(new Intent(Carga_Datos.this,ActivityHome.class));
                            finish();
                        } else {

                            Runnable runnable = new Runnable()
                            {

                                public void run()
                                {
                                    AlertDialog.Builder a = new AlertDialog.Builder(Carga_Datos.this);
                                    a.setIcon(android.R.drawable.ic_dialog_alert);
                                    a.setTitle("Error");
                                    a.setMessage("Error Haciendo la carga de datos por favor intente de Nuevo..");
                                    a.setPositiveButton(android.R.string.yes, null);
                                    a.setNegativeButton(android.R.string.no, null);
                                    a.show();
                                }
                            };

                            btnLogin.post(runnable);

                            //AlertCargaDatoFail();

                            Usuario.setText("");
                            Password.setText("");
                        }
                    } catch (Exception e) {
                        dialogProcess.dismiss();
                        Log.e("Ez-visit", "ERROR: ", e);
                    } finally {
                    }
                }

            }).start();
        //} else {
          //  AlertNoInternet();
        //}
    }

    public void AlertCargaDatoFail() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setIcon(android.R.drawable.ic_dialog_alert);
        a.setTitle("Error");
        a.setMessage("Error Haciendo la carga de datos por favor intente de Nuevo..");
        a.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        a.show();
    }


    public void ReportarProgreso(int Cantidad){
        myProcesosBarra = ((float)Cantidad / 10) * 100;
        p = Math.round(myProcesosBarra);
        dialogProcess.setProgress(p);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_carga__datos, menu);
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

    public void AlertNoInternet() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setIcon(android.R.drawable.ic_dialog_alert);
        a.setTitle("Conexión");
        a.setMessage("Usted no cuenta con conexión a internet. Active el 3G o Wifi");
        a.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        a.show();
    }


    // handler for the background updating
    Handler progressHandler = new Handler() {
        public void handleMessage(Message msg) {
			/*String Pro = "Procesando tabla ";
			dialogProcess.setMessage(Pro + ReferenciaPorcienMetod + ".");*/
            dialogProcess.setMessage("Cargando Datos");
            if (endSync) {
                dialogProcess .setMessage("La Carga de Dato ha Terminado.");
            }
        }
    };
}
