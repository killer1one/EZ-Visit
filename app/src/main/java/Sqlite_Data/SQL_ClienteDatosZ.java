package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_ClienteDatosZ {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_ClienteDatosZ;

    public SQL_ClienteDatosZ(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_ClienteDatosZ myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("RegID", myData.RegID);
            values.put("ClientID", myData.ClientID);
            values.put("MasterID", myData.MasterID);
            values.put("ZonaID", myData.ZonaID);
            values.put("EspecID", myData.EspecID);
            values.put("Clasificacion", myData.Clasificacion);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
