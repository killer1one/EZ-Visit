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
public class SQL_Productos {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_Productos;

    public SQL_Productos(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_Productos myData)
    {
            ContentValues values = new ContentValues();
            values.put("ProductID", myData.ProductID);
            values.put("MasterID", myData.MasterID);
            values.put("EspecID", myData.EspecID);
            values.put("Nombre", myData.Nombre);

            return db.insert(TABLE_NAME,null,values);

    }

    public SimpleAdapter getProductos(Context contexto)
    {


        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map = new HashMap<String, String>();

        String sql = "select Nombre, ProductID from "+TABLE_NAME+" " ;


        c = db.rawQuery(sql, null);

        if (c.moveToFirst())
        {
            //Recorremos el cursor hasta que no haya mas registros
            do {

                map = new HashMap<String, String>();
                map.put("Nombre",c.getString(0));

                mylist.add(map);
            } while(c.moveToNext());
        } // if ends here


        SimpleAdapter   idsAdapter  = new Row_TempAdapter(contexto, mylist, R.layout.row_producto,
                new String[] {"Nombre"},
                new int[] {R.id.txtNombreProducto});



        return idsAdapter;
    } // Method ends here


    public Cursor getCursor(){
        return c;
    }


}
