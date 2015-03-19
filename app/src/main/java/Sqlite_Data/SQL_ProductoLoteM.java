package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Carlos Nina Vargas on 3/19/2015.
 */
public class SQL_ProductoLoteM
{
    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_ProductoLoteM;

    public SQL_ProductoLoteM(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_ProductoLoteM myData)
    {
            ContentValues values = new ContentValues();
            values.put("LoteID", myData.LoteID);
            values.put("MasterID", myData.MasterID);
            values.put("PlanID", myData.PlanID);
            values.put("EmployeeID", myData.EmployeeID);
            values.put("Nota", myData.Nota);
            values.put("FechaCreacion", myData.FechaCreacion);
            values.put("FechaAprobacion", myData.FechaAprobacion);
            values.put("Aprobado", myData.Aprobado);

            return db.insert(TABLE_NAME,null,values);
    }

    // Este es para los labels o cualquier necesidad de saber el numero de lote
    public int getLoteID()
    {
        int result = -1;
        c = db.rawQuery("select LoteID from " + TABLE_NAME,null);
        if (c.moveToFirst())
        {
            result = c.getInt(0);
        }
        return result;
    }


}
