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
public class SQL_ClienteMaestro {


    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_ClienteMaestro;

    public SQL_ClienteMaestro(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_ClienteMaestro myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("ClientID", myData.ClientID);
            values.put("MasterID", myData.MasterID);
            values.put("ClientCode", myData.ClientCode);
            values.put("Clasificacion", myData.Clasificacion);
            values.put("ClientTipo", myData.ClientTipo);
            values.put("ClientNombre", myData.ClientNombre);
            values.put("ClientApellido", myData.ClientApellido);
            values.put("ClientContactID", myData.ClientContactID);
            values.put("ClientAddressID", myData.ClientAddressID);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String GetLastID()
    {
        String myResult;
        c = db.rawQuery("select ClientID as numeroC,max(cast(ClientID as integer)) from "+TABLE_NAME,null);
        if (c.moveToFirst())
        {
            myResult = c.getString(0);
        } else
        {
            myResult = "0";
        }
        return myResult;
    }


    public SimpleAdapter getCliente(Context contexto, String NombreCliente)
    {


        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map = new HashMap<String, String>();

        String sql = "select Clasificacion,ClientNombre, ClientApellido from "+TABLE_NAME+"  " +
                " where ClientNombre LIKE '%"+NombreCliente+"%' order by ClientNombre";


        c = db.rawQuery(sql, null);

        if (c.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya mas registros
                do {

                    map = new HashMap<String, String>();
                    map.put("DirTipo","Clasificacion: "+c.getString(0));
                    map.put("Telefono","Nombre: "+ c.getString(1));
                    map.put("Correo","Apellido: " +c.getString(2));

                mylist.add(map);
            } while(c.moveToNext());
        } // if ends here


        SimpleAdapter   idsAdapter  = new Row_TempAdapter(contexto, mylist, R.layout.row_contacto,
                new String[] {"DirTipo", "Telefono", "Correo"},
                new int[] {R.id.txtTipoDir,R.id.txtTelefono,R.id.txtCorreo});



        return idsAdapter;
    } // Method ends here


    public Cursor getCliente(){
     Cursor retun = null;
        retun = c;
        return retun;
    }

}
