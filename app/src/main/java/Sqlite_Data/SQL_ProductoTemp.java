package Sqlite_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ec_Colon on 15/2/15.
 */
public class SQL_ProductoTemp {

    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_ProductosTemp;

    public SQL_ProductoTemp(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_ProductosTemp myData)
    {
        try{
            ContentValues values = new ContentValues();
            values.put("Cantidad", myData.Cantidad);
            values.put("ProductID", myData.ProductID);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Cursor getCursor()
    {
        return c;
    }

    public void DeleteAll(){
        try {
            db.execSQL("Delete from "+TABLE_NAME);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public void DeleteItem(int ProductoID){
        try {
            db.execSQL("Delete from "+TABLE_NAME+" where ProductID = "+ProductoID);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


}
