package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_ClientAddress {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_ClientAddress;

    public SQL_ClientAddress(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_ClientAddress myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("ClientAddressID", myData.ClientAddressID);
            values.put("MasterID", myData.MasterID);
            values.put("ClientID", myData.ClientID);
            values.put("ClientDireccion", myData.ClientDireccion);
            values.put("ClientLongitud", myData.ClientLongitud);
            values.put("ClientLatitud", myData.ClientLatitud);
            values.put("TipoAddress", myData.TipoAddress);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int GetLastID()
    {
        int myResult;
        c = db.rawQuery("select ClientAddressID as numeroC,max(cast(ClientAddressID as integer)) from "+TABLE_NAME,null);
        if (c.moveToFirst())
        {
            myResult = c.getInt(0);
        } else
        {
            myResult = 0;
        }
        return myResult;
    }

    public void ActualizarCLienteID(int ClientID, int ClientAddressID){
        try {
            db.execSQL("update "+TABLE_NAME+" set ClientID = "+ClientID+" where ClientAddressID = "+ClientAddressID);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
