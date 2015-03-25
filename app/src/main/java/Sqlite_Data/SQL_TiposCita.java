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
public class SQL_TiposCita {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_TiposCita;

    public SQL_TiposCita(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_TiposCita myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("PTTipo", myData.PTTipo);
            values.put("Descripcion", myData.Descripcion);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public SelectableListAdapter<String> getTiposVisita(Context contexto) {
        ArrayList<String> idc = new ArrayList<String>();
        idc.add("(Seleccione tipo Visita)");
        idc.add("Solo Promoción");
        idc.add("Solo Muestras");
        idc.add("Promoción y Muestras");


        SelectableListAdapter<String> idsAdapter = new SelectableListAdapter<String>(contexto, android.R.layout.simple_list_item_1, idc);

        return idsAdapter;
    }
}

