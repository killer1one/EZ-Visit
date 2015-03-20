package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Util.SelectableListAdapter;

/**
 * Created by Carlos Nina Vargas on 3/19/2015.
 */
public class SQL_ProductoLoteD
{
    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_ProductoLoteD;

    public SQL_ProductoLoteD(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_ProductoLoteD myData)
    {
        ContentValues values = new ContentValues();
        values.put("RegID", myData.RegID);
        values.put("MasterID", myData.MasterID);
        values.put("LoteID", myData.LoteID);
        values.put("ProductID", myData.ProductID);
        values.put("Cantidad", myData.Cantidad);

        return db.insert(TABLE_NAME,null,values);
    }

    public SelectableListAdapter<String> GetListaLote(Context contexto) {
        ArrayList<String> idc = new ArrayList<String>();

        c = db.rawQuery("select b.Nombre, a.Cantidad, c.Aprobado from " + Sqlite.TABLE_ProductoLoteM + " c, " +TABLE_NAME+" a inner join " + Sqlite.TABLE_Productos + " b where a.ProductID = b.ProductID and c.Aprobado = 0 Order by a.ProductID", null);
        // Getting the data from the select
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya m�s registros
            do {
                // This its here only to pad numbers with 0
                if(c.getInt(1)<10)
                {
                    String nombre = "0"+ c.getString(1) + " - " + c.getString(0);
                    idc.add(nombre);
                } else
                {
                    String nombre = c.getString(1) + " - " + c.getString(0);
                    idc.add(nombre);
                }

            } while(c.moveToNext());
        } // if ends here

        SelectableListAdapter<String> idsAdapter = new SelectableListAdapter<String>(contexto, android.R.layout.simple_list_item_1, idc);

        return idsAdapter;
    }
}
