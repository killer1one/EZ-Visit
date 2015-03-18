package Sqlite_Data;

/**
 * Created by Ec_Colon on 12/2/15.
 */
public class Variable_Static {

    public static String UserMobile = "CREATE TABLE [UserMobile] (\n" +
            "[RegID] INTEGER PRIMARY KEY NOT NULL ,\n" +
            "[MasterID] INTEGER NOT NULL ,\n" +
            "[EmployeeID] INTEGER NOT NULL ,\n" +
            "[MobUser] VARCHAR( 32 ) NOT NULL ,\n" +
            "[MobPass] VARCHAR( 100 ) NOT NULL ,\n" +
            "[MobActive] INT NOT NULL DEFAULT '1' )";

    public static String UserMaster = "CREATE TABLE [UserMaster] (\n" +
            "[MasterID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
            "[MasUser] VARCHAR( 32 ) NOT NULL ,\n" +
            "[MasPass] VARCHAR( 100 ) NOT NULL ,\n" +
            "[MasName] VARCHAR( 200 ) NOT NULL ,\n" +
            "[MasActive] INT NOT NULL DEFAULT '1' )";

    public static String ClienteMaestro = "CREATE TABLE [ClienteMaestro] (\n" +
            "[ClientID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "[MasterID] INT UNSIGNED NOT NULL ,\n" +
            "[ClientCode] VARCHAR( 30 ) NOT NULL ,\n" +
            "[Clasificacion] VARCHAR( 3 ) NOT NULL ,\n" +
            "[ClientTipo] INT UNSIGNED NOT NULL ,\n" +
            "[ClientNombre] VARCHAR( 200 ) NOT NULL ,\n" +
            "[ClientApellido] VARCHAR( 150 ) NULL ,\n" +
            "[ClientContactID] INT UNSIGNED NOT NULL ,\n" +
            "[ClientAddressID] INT UNSIGNED NOT NULL )";

    public static String ClientTipos = "CREATE TABLE [ClientTipos] (\n" +
            "[ClientTipo] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL  ,\n" +
            "[Descripcion] VARCHAR( 100 ) NOT NULL )";

    public static String ClienteContacto = "CREATE TABLE [ClienteContacto] (\n" +
            "[ClientContactID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL  ,\n" +
            "[MasterID] INT UNSIGNED NOT NULL ,\n" +
            "[ClientID] INT UNSIGNED NOT NULL ,\n" +
            "[TipoContacto] INT UNSIGNED NOT NULL ,\n" +
            "[NumeroTel] VARCHAR( 13 ) NULL  ,\n" +
            "[ClientCorreo] VARCHAR( 200 ) NULL )";

    public static String ContactoTipos = "CREATE TABLE [ContactoTipos] (\n" +
            "[TipoContacto] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
            "[Descripcion] VARCHAR( 100 ) NOT NULL )";

    public static String ClientAddress = "CREATE TABLE [ClientAddress] (\n" +
            "[ClientAddressID] INT UNSIGNED NOT NULL ,\n" +
            "[MasterID] INT UNSIGNED NOT NULL ,\n" +
            "[ClientID] INT UNSIGNED NOT NULL ,\n" +
            "[ClientDireccion] VARCHAR( 250 ) NOT NULL ,\n" +
            "[ClientLongitud] VARCHAR( 250 ) NULL ,\n" +
            "[ClientLatitud] VARCHAR( 250 ) NULL ,\n" +
            "[TipoAddress] INT UNSIGNED NOT NULL ,\n" +
            "PRIMARY KEY ( [ClientAddressID] ))";

    public static String TipoAddresses = "CREATE TABLE [TipoAddresses] (\n" +
            "[TipoAddress] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
            "[Descripcion] VARCHAR( 100 ) NOT NULL \n" +
            ") ";

    public static String ClientAsignados = "CREATE TABLE [ClientAsignados] (\n" +
            "[RegID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "[MasterID] INT UNSIGNED NOT NULL ,\n" +
            "[ClientID] INT UNSIGNED NOT NULL ,\n" +
            "[EmployeeID] INT UNSIGNED NOT NULL )";

    public static String Employee = "CREATE TABLE [Employee] (\n" +
            "[EmployeeID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
            "[EmployeeCode] VARCHAR( 30 ) NOT NULL ,\n" +
            "[MasterID] INT UNSIGNED NOT NULL ,\n" +
            "[SuperID] INT UNSIGNED NULL ,\n" +
            "[EmpNombre] VARCHAR( 75 ) NOT NULL ,\n" +
            "[EmpApellido] VARCHAR( 150 ) NOT NULL ,\n" +
            "[EmployeeTipo] INT UNSIGNED NOT NULL ,\n" +
            "UNIQUE (\n" +
            "[EmployeeCode]))";

    public static String EmployeeTipos = "CREATE TABLE [EmployeeTipos] (\n" +
            "[EmployeeTipo] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "[Descripcion] VARCHAR( 100 ) NOT NULL )";

    public static String PlanTrabajo = "CREATE TABLE [PlanTrabajo] (\n" +
            "[RegID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "[MasterID] INTEGER NOT NULL ,\n" +
            "[EmployeeID] INTEGER NOT NULL ,\n" +
            "[ClientID] INTEGER NOT NULL ,\n" +
            "[PTHora] TIME NOT NULL ,\n" +
            "[PTFecha] DATE NOT NULL ,\n" +
            "[PTEstado] INT NOT NULL ,\n" +
            "[PTTipo] INTEGER NOT NULL ,\n" +
            "[PTPRog] INTEGER NOT NULL ,\n" +
            "[SuperID] INTEGER UNSIGNED NOT NULL)";

    public static String TiposCita = "CREATE TABLE [TiposCita] (\n" +
            "[PTTipo] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "[Descripcion] VARCHAR( 100 ) NOT NULL ) ";

    public static String HistorialDes = "CREATE TABLE [HistorialDes] (\n" +
            "[RegID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "[MasterID] INT UNSIGNED NOT NULL ,\n" +
            "[EmployeeID] INT UNSIGNED NOT NULL ,\n" +
            "[HDEstado] INT NOT NULL ,\n" +
            "[HDNotas] TEXT NOT NULL ,\n" +
            "[HDFecha] DATE NOT NULL ,\n" +
            "[HDHora] TIME NOT NULL )";

    public static String ConfiguracionKPI = "CREATE TABLE [ConfiguracionKPI] (\n" +
            "[RegID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "[EmployeeID] INT UNSIGNED NOT NULL ,\n" +
            "[MasterID] INT UNSIGNED NOT NULL ,\n" +
            "[ConfigID] INT UNSIGNED NOT NULL )";

    public static String ListaKPI = "CREATE TABLE [ListaKPI] (\n" +
            "[ConfigID] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,\n" +
            "[Descripcion] VARCHAR( 100 ) NOT NULL )";

    public static String ClienteDatosZ = "CREATE TABLE [ClienteDatosZ] (\n" +
            "[RegID] INTEGER NOT NULL ," +
            "[ClientID] INTEGER NOT NULL, " +
            "[MasterID] INTEGER NOT NULL, " +
            "[ZonaID] INTEGER NOT NULL, " +
            "[EspecID] INTEGER NOT NULL, \n" +
            "[Clasificacion] VARCHAR( 100 ) NOT NULL )";

    public static String Especialidades = "CREATE TABLE [Especialidades] (\n" +
            "[EspecID] INTEGER NOT NULL ," +
            "[MasterID] INTEGER NOT NULL, " +
            "[Descripcion] VARCHAR( 100 ) NOT NULL )";

    public static String Parrilla = "CREATE TABLE [Parrilla] (\n" +
            "[RegID] INTEGER NOT NULL ," +
            "[MasterID] INTEGER NOT NULL, " +
            "[PlanID] INTEGER NOT NULL, " +
            "[EspecID] INTEGER NOT NULL, " +
            "[ProductID] INTEGER NOT NULL, \n" +
            "[SugA] VARCHAR( 100 ) NOT NULL, \n" +
            "[SugB] VARCHAR( 100 ) NOT NULL, \n" +
            "[SugC] VARCHAR( 100 ) NOT NULL )";

    public static String Productos = "CREATE TABLE [Productos] (\n" +
            "[ProductID] INTEGER NOT NULL ," +
            "[MasterID] INTEGER NOT NULL, " +
            "[EspecID] INTEGER NOT NULL, " +
            "[Nombre] VARCHAR( 100 ) NOT NULL )";



    /*Tablas Temporales*/

    public static String DirrecionesTemp = "CREATE TABLE [DirrecionTemp] (\n" +
                "[DirTipo] INTEGER NOT NULL ,\n" +
                "[Direccion] VARCHAR( 100 ) NOT NULL )";

    public static String ContactoTemp = "CREATE TABLE [ContactoTemp] (\n" +
            "[DirTipo] INTEGER NOT NULL ,\n" +
            "[Telefono] VARCHAR( 100 ) NOT NULL, " +
            "[Correo] VARCHAR( 100 ) NOT NULL )";



}
