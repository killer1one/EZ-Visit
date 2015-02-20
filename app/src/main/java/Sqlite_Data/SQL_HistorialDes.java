package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_HistorialDes {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_HistorialDes;

    public SQL_HistorialDes(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_HistorialDes myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("RegID", myData.RegID);
            values.put("MasterID", myData.MasterID);
            values.put("EmployeeID", myData.EmployeeID);
            values.put("HDEstado", myData.HDEstado);
            values.put("HDNotas", myData.HDNotas);
            values.put("HDFecha", myData.HDFecha);
            values.put("HDHora", myData.HDHora);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
