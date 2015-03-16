package Sqlite_Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Carlos Nina Vargas on 3/16/2015.
 */
public class SQL_UserMobile
{
    private Sqlite myConn;
    private SQLiteDatabase db;
    private Cursor c;
    private static final String TABLE_NAME = Sqlite.TABLE_UserMobile;

    public SQL_UserMobile(Context context)
    {
        myConn = new Sqlite(context, null, 1);
        db = myConn.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }

    public long saveRecord(Data_UserMobile myData)
    {
        try{
            ContentValues values = new ContentValues();

            values.put("RegID", myData.RegID);
            values.put("MasterID", myData.MasterID);
            values.put("EmployeeID", myData.EmployeeID);
            values.put("MobUser", myData.MobUser);
            values.put("MobPass", myData.MobPass);
            values.put("MobActive", myData.MobActive);

            return db.insert(TABLE_NAME,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

  public Boolean checkLogin(String User, String Password)
  {
      Password = computeMD5Hash(Password);
      try {
          c = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " where MobUser = '" + User + "' and MobPass = '" + Password + "'", null);
          if (c.moveToFirst()) {
                  return true;
              } else {
                  return false;
          }
      }
      catch(Exception e)
      {
        return false;
      }
  }

    public String computeMD5Hash(String password)
    {
        try {
            String result = password;
            if(password != null) {
                MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
                md.update(password.getBytes());
                BigInteger hash = new BigInteger(1, md.digest());
                result = hash.toString(16);
                while(result.length() < 32) { //40 for SHA-1
                    result = "0" + result;
                }
            }
            return result;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
