package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_PlanTrabajo {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_PlanTrabajo;

    public SQL_PlanTrabajo(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_PlanTrabajo myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("RegID", myData.RegID);
            values.put("MasterID", myData.MasterID);
            values.put("EmployeeID", myData.EmployeeID);
            values.put("ClientID", myData.ClientID);
            values.put("PTHora", myData.PTHora);
            values.put("PTFecha", myData.PTFecha);
            values.put("PTEstado", myData.PTEstado);
            values.put("PTTipo", myData.PTTipo);
            values.put("PTPRog", myData.PTPRog);
            values.put("SuperID", myData.SuperID);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
