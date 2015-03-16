package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Util.SelectableListAdapter;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_ClientTipos {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_ClientTipos;

    public SQL_ClientTipos(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_ClientTipos myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("ClientTipo", myData.ClientTipo);
            values.put("Descripcion", myData.Descripcion);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public SelectableListAdapter<String> ListaClienteTipo(Context contexto) {
        ArrayList<String> idc = new ArrayList<String>();
        idc.add("(Seleccione)");

        c = db.rawQuery("select Descripcion from "+TABLE_NAME+" Order by Descripcion", null);
        // Getting the data from the select
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya m�s registros
            do {
                String nombre = c.getString(0);
                idc.add(nombre);
            } while(c.moveToNext());
        } // if ends here

        SelectableListAdapter<String> idsAdapter = new SelectableListAdapter<String>(contexto, android.R.layout.simple_list_item_1, idc);

        return idsAdapter;
    }

    public SelectableListAdapter<String> ListaClasificacion(Context contexto) {
        ArrayList<String> idc = new ArrayList<String>();
        idc.add("(Seleccione)");
        idc.add("A");
        idc.add("B");
        idc.add("C");

        SelectableListAdapter<String> idsAdapter = new SelectableListAdapter<String>(contexto, android.R.layout.simple_list_item_1, idc);

        return idsAdapter;
    }


    public int getCLienteTipo(String Descripcion)
    {
        int myResult;
        c = db.rawQuery("Select ClientTipo from "+TABLE_NAME+" where Descripcion = '"+Descripcion+"' ",null);
        if (c.moveToFirst())
        {
            myResult = c.getInt(0);
        } else
        {
            myResult = 0;
        }
        return myResult;
    }

}
