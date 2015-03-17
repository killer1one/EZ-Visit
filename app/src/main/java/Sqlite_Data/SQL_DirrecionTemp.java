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
public class SQL_DirrecionTemp {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_DirrecionTemp;

    public SQL_DirrecionTemp(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_DirrecionTemp myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("DirTipo", myData.DirTipo);
            values.put("Direccion", myData.Direccion);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



    public SimpleAdapter ShowDirTemp(Context contexto)
    {


        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map = new HashMap<String, String>();

        String sql = "select Temp.Direccion, CT.Descripcion from "+TABLE_NAME+" Temp inner join ClientTipos CT on Temp.DirTipo = CT.ClientTipo order by Temp.Direccion";


        c = db.rawQuery(sql, null);

        if (c.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya mas registros
            do {

                map = new HashMap<String, String>();
                map.put("DirTipo",c.getString(1));
                map.put("Direccion", c.getString(0));


                mylist.add(map);
            } while(c.moveToNext());
        } // if ends here


        SimpleAdapter   idsAdapter  = new Row_TempAdapter(contexto, mylist, R.layout.row_direciones,
                    new String[] {"DirTipo", "Direccion"},  new int[] {R.id.txtTipoDir,R.id.txtDirecion});



        return idsAdapter;
    } // Method ends here

    public void getAllData(Context contexto)
    {
        c = db.rawQuery("SELECT DirTipo, Direccion FROM " + TABLE_NAME, null);

    } // Method ends here

    public void getDeleteAll()
    {
        db.execSQL("delete from "+TABLE_NAME);

    } // Method ends here

    public Cursor getCursor()
    {
        return c;
    }


}
