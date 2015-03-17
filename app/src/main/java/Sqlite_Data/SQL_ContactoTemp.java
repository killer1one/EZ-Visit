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
public class SQL_ContactoTemp {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_ContactoTemp;

    public SQL_ContactoTemp(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_ContactoTemp myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("DirTipo", myData.DirTipo);
            values.put("Telefono", myData.Telefono);
            values.put("Correo", myData.Correo);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



    public SimpleAdapter ShowConTemp(Context contexto)
    {


        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map = new HashMap<String, String>();

        String sql = "select Temp.Telefono,Temp.Correo, CT.Descripcion from "+TABLE_NAME+" Temp inner join ClientTipos CT on Temp.DirTipo = CT.ClientTipo order by Temp.Correo";


        c = db.rawQuery(sql, null);

        if (c.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya mas registros
            do {

                map = new HashMap<String, String>();
                map.put("DirTipo",c.getString(2));
                map.put("Telefono", c.getString(0));
                map.put("Correo", c.getString(1));


                mylist.add(map);
            } while(c.moveToNext());
        } // if ends here


        SimpleAdapter   idsAdapter  = new Row_TempAdapter(contexto, mylist, R.layout.row_Contacto,
                    new String[] {"DirTipo", "Telefono", "Correo"},  new int[] {R.id.txtTipoDir,R.id.txtTelefono,R.id.txtCorreo});



        return idsAdapter;
    } // Method ends here


    public void getAllData(Context contexto)
    {
        c = db.rawQuery("SELECT DirTipo, Telefono, Correo FROM " + TABLE_NAME, null);

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
