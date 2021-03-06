package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_Parrilla {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_Parrilla;

    public SQL_Parrilla(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_Parrilla myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("RegID", myData.RegID);
            values.put("MasterID", myData.MasterID);
            values.put("PlanID", myData.PlanID);
            values.put("EspecID", myData.EspecID);
            values.put("ProductID", myData.ProductID);
            values.put("SugA", myData.SugA);
            values.put("SugB", myData.SugB);
            values.put("SugC", myData.SugC);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
