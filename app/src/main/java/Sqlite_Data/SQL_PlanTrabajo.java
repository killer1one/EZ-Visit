package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import Util.Row_TempAdapter;
import visit.ez.wyse.ezvisit.R;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_PlanTrabajo {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_PlanTrabajo;

    public SQL_PlanTrabajo(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_PlanTrabajo myData)
    {
        try{
            ContentValues values = new ContentValues();
            //values.put("RegID", myData.RegID);
            values.put("MasterID", myData.MasterID);
            values.put("EmployeeID", myData.EmployeeID);
            values.put("ClientID", myData.ClientID);
            values.put("PTHora", myData.PTHora);
            values.put("PTFecha", myData.PTFecha);
            values.put("PTEstado", myData.PTEstado);
            values.put("PTTipo", myData.PTTipo);
            values.put("PTPRog", myData.PTPRog);
            values.put("SuperID", myData.SuperID);

            values.put("VisitID", myData.VisitID);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public SimpleAdapter getPlanTrabajo(Context contexto)
    {


        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map = new HashMap<String, String>();

        String sql = "select CM.ClientCode ,CM.ClientNombre, CA.ClientDireccion, PL.PTEstado,CM.ClientApellido, PL.RegID, Pl.VisitID  from "+TABLE_NAME+" PL  " +
                "inner join ClienteMaestro CM on CM.ClientID = PL.ClientID  " +
                "inner join ClientAddress CA on CA.ClientID = CM.ClientID" ;


        c = db.rawQuery(sql, null);

        if (c.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya mas registros
            do {

                map = new HashMap<String, String>();
                map.put("Codigo",c.getString(0));
                map.put("Nombre",c.getString(1));
                map.put("Direccion",c.getString(2));
                if(c.getInt(3) == 0){
                    map.put("Aprovacion", "pendiente de aprobación");
                }else{
                    map.put("Aprovacion", "Visitado");
                }


                mylist.add(map);
            } while(c.moveToNext());
        } // if ends here


        SimpleAdapter   idsAdapter  = new Row_TempAdapter(contexto, mylist, R.layout.row_plan_trabajo,
                new String[] {"Codigo", "Nombre", "Direccion", "Aprovacion"},
                new int[] {R.id.txtCodigo,R.id.txtNombreCliente,R.id.txtDireccion, R.id.txtAprovacion});



        return idsAdapter;
    } // Method ends here

    public int GetVisitID()
    {
        int myResult;
        c = db.rawQuery("select VisitID as numeroC,max(cast(VisitID as integer)) from "+TABLE_NAME,null);
        if (c.moveToFirst())
        {
            myResult = c.getInt(0);
        } else
        {
            myResult = 0;
        }
        return myResult;
    }


    public Cursor getPlanT(){
        return c;
    }


    public void ActualizarEstatus(int RepID){
        try {
            db.execSQL("update "+TABLE_NAME+" set PTEstado = 1 where RegID = "+RepID);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
