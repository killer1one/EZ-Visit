package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_Employee {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_Employee;

    public SQL_Employee(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_Employee myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("EmployeeID", myData.EmployeeID);
            values.put("EmployeeCode", myData.EmployeeCode);
            values.put("MasterID", myData.MasterID);
            values.put("SuperID", myData.SuperID);
            values.put("EmpNombre", myData.EmpNombre);
            values.put("EmpApellido", myData.EmpApellido);
            values.put("EmployeeTipo", myData.EmployeeTipo);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
