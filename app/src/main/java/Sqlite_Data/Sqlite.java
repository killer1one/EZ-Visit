package Sqlite_Data;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

public class Sqlite extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "EZ-VISIT.db";
	private static File BASE_PATH = Environment.getDataDirectory();
	public static final String DATA_DIRNAME = "EZ-VISIT";
	public static final String DATABASE_PATH = Environment.getDataDirectory().getAbsolutePath() + File.separator + DATA_DIRNAME + File.separator + DATABASE_NAME;
    //public static final String DATABASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + DATA_DIRNAME + File.separator + DATABASE_NAME;
    public static final int DATABASE_VERSION = 1;

	// Table List
	public static final String TABLE_UserMaster = "UserMaster";
    public static final String TABLE_UserMobile = "UserMobile";
    public static final String TABLE_ClienteMaestro = "ClienteMaestro";
    public static final String TABLE_ClientTipos = "ClientTipos";
    public static final String TABLE_ClienteContacto = "ClienteContacto";
    public static final String TABLE_ContactoTipos = "ContactoTipos";
    public static final String TABLE_ClientAddress = "ClientAddress";
    public static final String TABLE_TipoAddresses = "TipoAddresses";
    public static final String TABLE_ClientAsignados = "ClientAsignados";
    public static final String TABLE_Employee = "Employee";
    public static final String TABLE_EmployeeTipos = "EmployeeTipos";
    public static final String TABLE_PlanTrabajo = "PlanTrabajo";
    public static final String TABLE_TiposCita = "TiposCita";
    public static final String TABLE_HistorialDes = "HistorialDes";
    public static final String TABLE_ConfiguracionKPI = "ConfiguracionKPI";
    public static final String TABLE_ListaKPI = "ListaKPI";
    public static final String TABLE_ClienteDatosZ = "ClienteDatosZ";
    public static final String TABLE_Especialidades = "Especialidades";
    public static final String TABLE_Parrilla = "Parrilla";
    public static final String TABLE_Productos = "Productos";
    public static final String TABLE_ProductoLoteM = "ProductoLoteM";
    public static final String TABLE_ProductoLoteD = "ProductoLoteD";
    public static final String TABLE_Inventario = "Inventario";



	// Creation SQL
	private static final String DATABASE_CREATE_UserMaster = Variable_Static.UserMaster;
    private static final String DATABASE_CREATE_UserMobile = Variable_Static.UserMobile;
    private static final String DATABASE_CREATE_ClienteMaestro = Variable_Static.ClienteMaestro;
    private static final String DATABASE_CREATE_ClientTipos = Variable_Static.ClientTipos;
    private static final String DATABASE_CREATE_ClienteContacto = Variable_Static.ClienteContacto;
    private static final String DATABASE_CREATE_ContactoTipos = Variable_Static.ContactoTipos;
    private static final String DATABASE_CREATE_ClientAddress = Variable_Static.ClientAddress;
    private static final String DATABASE_CREATE_TipoAddresses = Variable_Static.TipoAddresses;
    private static final String DATABASE_CREATE_ClientAsignados = Variable_Static.ClientAsignados;
    private static final String DATABASE_CREATE_Employee = Variable_Static.Employee;
    private static final String DATABASE_CREATE_EmployeeTipos = Variable_Static.EmployeeTipos;
    private static final String DATABASE_CREATE_PlanTrabajo = Variable_Static.PlanTrabajo;
    private static final String DATABASE_CREATE_TiposCita = Variable_Static.TiposCita;
    private static final String DATABASE_CREATE_HistorialDes = Variable_Static.HistorialDes;
    private static final String DATABASE_CREATE_ConfiguracionKPI = Variable_Static.ConfiguracionKPI;
    private static final String DATABASE_CREATE_ListaKPI = Variable_Static.ListaKPI;
    private static final String DATABASE_CREATE_ClienteDatosZ = Variable_Static.ClienteDatosZ;
    private static final String DATABASE_CREATE_Especialidades = Variable_Static.Especialidades;
    private static final String DATABASE_CREATE_Parrilla = Variable_Static.Parrilla;
    private static final String DATABASE_CREATE_Productos = Variable_Static.Productos;
    private static final String DATABASE_CREATE_ProductoLoteM = Variable_Static.ProductoLoteM;
    private static final String DATABASE_CREATE_ProductoLoteD = Variable_Static.ProductoLoteD;

    private static final String DATABASE_CREATE_Inventario = Variable_Static.Inventario;




    //Tablas Temporales
    public static final String TABLE_DirrecionTemp = "DirrecionTemp";
    private static final String DATABASE_CREATE_DirrecionTemp = Variable_Static.DirrecionesTemp;

    public static final String TABLE_ContactoTemp = "ContactoTemp";
    private static final String DATABASE_CREATE_ContactoTemp = Variable_Static.ContactoTemp;

    public static final String TABLE_ProductosTemp = "ProductosTemp";
    private static final String DATABASE_CREATE_ProductosTemp = Variable_Static.ProductosTemp;





    // Constructor here
	public Sqlite(Context contexto, CursorFactory factory, int version) {
        //super(contexto, nombre, factory, version);
        super(new ContextWrapper(contexto) {
            @Override
            public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory) {

                // allow database directory to be specified
                File dir = new File(BASE_PATH.getAbsolutePath() + File.separator + DATA_DIRNAME);
                if(!dir.exists()) {
                    dir.mkdirs();
                }
                return SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            }
        }, DATABASE_NAME, null, DATABASE_VERSION);
        //this.context = contexto;

    }

	// Executing all SQL Queries for creation
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
            db.execSQL( DATABASE_CREATE_UserMaster );
            db.execSQL( DATABASE_CREATE_UserMobile );
            db.execSQL( DATABASE_CREATE_ClienteMaestro );
            db.execSQL( DATABASE_CREATE_ClientTipos );
            db.execSQL( DATABASE_CREATE_ClienteContacto );
            db.execSQL( DATABASE_CREATE_ContactoTipos );
            db.execSQL( DATABASE_CREATE_ClientAddress );
            db.execSQL( DATABASE_CREATE_TipoAddresses );
            db.execSQL( DATABASE_CREATE_ClientAsignados );
            db.execSQL( DATABASE_CREATE_Employee );
            db.execSQL( DATABASE_CREATE_EmployeeTipos );
            db.execSQL( DATABASE_CREATE_PlanTrabajo );
            db.execSQL( DATABASE_CREATE_TiposCita );
            db.execSQL( DATABASE_CREATE_HistorialDes );
            db.execSQL( DATABASE_CREATE_ConfiguracionKPI );
            db.execSQL( DATABASE_CREATE_ListaKPI );
            db.execSQL( DATABASE_CREATE_ClienteDatosZ );
            db.execSQL( DATABASE_CREATE_Especialidades );
            db.execSQL( DATABASE_CREATE_Parrilla );
            db.execSQL( DATABASE_CREATE_Productos );
            db.execSQL( DATABASE_CREATE_ProductoLoteM);
            db.execSQL( DATABASE_CREATE_ProductoLoteD);
            db.execSQL( DATABASE_CREATE_Inventario);

            //Temp
            db.execSQL( DATABASE_CREATE_DirrecionTemp );
            db.execSQL( DATABASE_CREATE_ContactoTemp );
            db.execSQL( DATABASE_CREATE_ProductosTemp );

        } catch (Exception e) {
            e.printStackTrace();
			// TODO: handle exception
		}
	}

    public Sqlite(Context context){

        super(context, DATABASE_PATH, null, DATABASE_VERSION);
    }

	@Override
	 public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva)
	{
		try {
			// Destroying everything
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_UserMaster );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_UserMobile );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ClienteMaestro );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ClientTipos );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ClienteContacto );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ContactoTipos );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ClientAddress );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TipoAddresses );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ClientAsignados );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Employee );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EmployeeTipos );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PlanTrabajo );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TiposCita );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HistorialDes );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ConfiguracionKPI );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ListaKPI );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ClienteDatosZ );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Especialidades );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Parrilla );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Productos );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ProductoLoteM );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ProductoLoteD );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Inventario);

            //Temp
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DirrecionTemp );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ContactoTemp );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ProductosTemp );

            // Calling to re-create everything
			onCreate(db);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
