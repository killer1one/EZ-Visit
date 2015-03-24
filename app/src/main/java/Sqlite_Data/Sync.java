package Sqlite_Data;

import android.content.Context;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import Util.Configuracion;
import visit.ez.wyse.ezvisit.Carga_Datos;

/**
 * Created by Ec_Colon on 23/2/15.
 */
public class Sync {

    // Result Codes
    public static int ERROR_FAILEDLOGIN = -10;
    public static int SUCCESSFUL_LOGIN = 1;

    private Carga_Datos _activityCargaDatos;

    public void setActivityCargaDatos(Carga_Datos activityCargaDatos)
    {
        _activityCargaDatos = activityCargaDatos;
    }

    public void getDeliverEdits (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getDeliverEdits");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getDeliverEdits", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();


                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getZonas (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getZonas");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getZonas", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();
                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);

                try {
                    SQL_Zonas myLisKPI = new SQL_Zonas(_Cont);
                    Data_Zonas Data = new Data_Zonas();

                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{ZonaID=-10; MasterID=anyType{}; Descripcion=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.ZonaID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.Descripcion = ic.getProperty(2).toString().trim();

                        myLisKPI.saveRecord(Data);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }

    public void getListaKPI (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getListaKPI");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getListaKPI", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ListaKPI myLisKPI = new SQL_ListaKPI(_Cont);
                Data_ListaKPI Data = new Data_ListaKPI();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);

                        Data.ConfigID = 0;
                        Data.Descripcion = "";

                        myLisKPI.saveRecord(Data);


                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getConfiguracionKPI (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getConfiguracionKPI");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getConfiguracionKPI", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();
                SQL_ConfiguracionKPI myConfKPI = new SQL_ConfiguracionKPI(_Cont);
                Data_ConfiguracionKPI Data = new Data_ConfiguracionKPI();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);

                        Data.ConfigID = 0;
                        Data.EmployeeID = 0;
                        Data.MasterID = 0;
                        Data.RegID = 0;


                        myConfKPI.saveRecord(Data);



                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getHistorialDes (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getHistorialDes");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getHistorialDes", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_HistorialDes myHisfKPI = new SQL_HistorialDes(_Cont);
                Data_HistorialDes Data = new Data_HistorialDes();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);

                        Data.EmployeeID = 0;
                        Data.HDEstado = 0;
                        Data.HDFecha = "";
                        Data.HDHora = "";
                        Data.HDNotas = "";
                        Data.MasterID = 0;
                        Data.RegID = 0;

                        myHisfKPI.saveRecord(Data);


                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getPTTipos (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getPTTipos");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getPTTipos", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();
                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);

                SQL_PTTipos myHisfKPI = new SQL_PTTipos(_Cont);
                Data_PTTipos Data = new Data_PTTipos();

                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{PTTipos=anyType{}; Descripcion=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.PTTipo = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.Descripcion = ic.getProperty(0).toString().trim();

                        myHisfKPI.saveRecord(Data);

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getPlanTrabajo (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getPlanTrabajo");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getPlanTrabajo", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_PlanTrabajo myPlan = new SQL_PlanTrabajo(_Cont);
                Data_PlanTrabajo Data = new Data_PlanTrabajo();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);


                        Data.ClientID = 0;
                        Data.EmployeeID = 0;
                        Data.MasterID = 0;
                        Data.PTEstado = 0;
                        Data.PTFecha = "";
                        Data.PTHora = "";
                        Data.PTPRog = 0;
                        Data.PTTipo = 0;
                        Data.RegID = 0;
                        Data.SuperID = 0;

                        myPlan.saveRecord(Data);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getEmployeeTipos (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getEmployeeTipos");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getEmployeeTipos", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_EmployeeTipos myEmpl = new SQL_EmployeeTipos(_Cont);
                Data_EmployeeTipos Data = new Data_EmployeeTipos();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{EmployeeTipo=-10; Descripcion=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.EmployeeTipo = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.Descripcion = ic.getProperty(1).toString().trim();

                        myEmpl.saveRecord(Data);

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public int getEmployee (String user, String pass, int ciclo, Context _Cont){

        int Success = 0;
        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getEmployee");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getEmployee", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_Employee myEmpl = new SQL_Employee(_Cont);
                Data_Employee Data = new Data_Employee();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                //SoapObject root_ic = (SoapObject)resSoap.getProperty(0);

                // Getting all sub-elements of responseMsg
                SoapObject ic_temp = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < ic_temp.getPropertyCount(); i++)
                    {

                        SoapObject  ic = (SoapObject)ic_temp.getProperty(i);

                        //anyType{EmployeeID=-10; EmployeeCode=anyType{}; MasterID=anyType{}; SuperID=anyType{}; EmpNombre=anyType{}; EmpApellido=anyType{}; EmployeeTipo=anyType{}; ZonaID=anyType{}; }
                        Data.EmployeeID = Integer.valueOf(ic.getProperty(0).toString().trim());


                        if(Data.EmployeeID==-10)
                        {
                            // Password is incorrect
                            Success = ERROR_FAILEDLOGIN;
                        } else
                        {
                            // Password is Correct so let's continuie
                            Data.EmployeeCode = ic.getProperty(1).toString().trim();
                            Data.MasterID = Integer.valueOf(ic.getProperty(2).toString().trim());
                            //SuperID
                            Data.EmpNombre = ic.getProperty(4).toString().trim();
                            Data.EmpApellido = ic.getProperty(5).toString().trim();
                            Data.EmployeeTipo = Integer.valueOf(ic.getProperty(6).toString().trim());

                            myEmpl.saveRecord(Data);
                            Success = SUCCESSFUL_LOGIN;
                        }

                    } // The For Ends here


                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

        return Success;
    }


    //////////////////////////////////////////////////

    public int getProductosLoteM (String user, String pass, int ciclo, Context _Cont){

        int Success = 0;
        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getProductoLoteM");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getProductoLoteM", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ProductoLoteM myProLM = new SQL_ProductoLoteM(_Cont);
                Data_ProductoLoteM Data = new Data_ProductoLoteM();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                //SoapObject root_ic = (SoapObject)resSoap.getProperty(0);

                // Getting all sub-elements of responseMsg
                SoapObject ic_temp = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < ic_temp.getPropertyCount(); i++)
                    {

                        SoapObject  ic = (SoapObject)ic_temp.getProperty(i);

                        //anyType{EmployeeID=-10; EmployeeCode=anyType{}; MasterID=anyType{}; SuperID=anyType{}; EmpNombre=anyType{}; EmpApellido=anyType{}; EmployeeTipo=anyType{}; ZonaID=anyType{}; }
                        Data.LoteID = Integer.valueOf(ic.getProperty(0).toString().trim());


                        if(Data.LoteID==-10)
                        {
                            // Password is incorrect
                            Success = ERROR_FAILEDLOGIN;
                        } else
                        {
                            // Password is Correct so let's continue
                            Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                            Data.PlanID = Integer.valueOf(ic.getProperty(2).toString().trim());
                            Data.EmployeeID = Integer.valueOf(ic.getProperty(3).toString().trim());
                            Data.Nota = ic.getProperty(4).toString().trim();
                            Data.FechaCreacion = ic.getProperty(5).toString().trim();
                            Data.FechaAprobacion = ic.getProperty(6).toString().trim();
                            Data.Aprobado = Integer.valueOf(ic.getProperty(7).toString().trim());

                            myProLM.saveRecord(Data);
                            Success = SUCCESSFUL_LOGIN;
                        }

                    } // The For Ends here


                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

        return Success;
    }

    public int getProductosLoteD (String user, String pass, int ciclo, Context _Cont){

        int Success = 0;
        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getProductoLoteD");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getProductoLoteD", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ProductoLoteD myProLM = new SQL_ProductoLoteD(_Cont);
                Data_ProductoLoteD Data = new Data_ProductoLoteD();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                //SoapObject root_ic = (SoapObject)resSoap.getProperty(0);

                // Getting all sub-elements of responseMsg
                SoapObject ic_temp = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < ic_temp.getPropertyCount(); i++)
                    {

                        SoapObject  ic = (SoapObject)ic_temp.getProperty(i);

                        //anyType{EmployeeID=-10; EmployeeCode=anyType{}; MasterID=anyType{}; SuperID=anyType{}; EmpNombre=anyType{}; EmpApellido=anyType{}; EmployeeTipo=anyType{}; ZonaID=anyType{}; }
                        Data.RegID = Integer.valueOf(ic.getProperty(0).toString().trim());


                        if(Data.RegID==-10)
                        {
                            // Password is incorrect
                            Success = ERROR_FAILEDLOGIN;
                        } else
                        {
                            // Password is Correct so let's continue
                            Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                            Data.LoteID = Integer.valueOf(ic.getProperty(2).toString().trim());
                            Data.ProductID = Integer.valueOf(ic.getProperty(3).toString().trim());
                            Data.Cantidad = Integer.valueOf(ic.getProperty(4).toString().trim());

                            myProLM.saveRecord(Data);
                            Success = SUCCESSFUL_LOGIN;
                        }

                    } // The For Ends here


                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

        return Success;
    }

    ///////////////////////////////////////////////////

    public int getUserMobile (String user, String pass, int ciclo, Context _Cont){

        int Success = 0;
        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getUserMobile");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getUserMobile", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_UserMobile myUser = new  SQL_UserMobile(_Cont);
                Data_UserMobile Data = new Data_UserMobile();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                //SoapObject root_ic = (SoapObject)resSoap.getProperty(0);

                // Getting all sub-elements of responseMsg
                SoapObject ic_temp = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < ic_temp.getPropertyCount(); i++)
                    {

                        SoapObject  ic = (SoapObject)ic_temp.getProperty(i);

                        //anyType{EmployeeID=-10; EmployeeCode=anyType{}; MasterID=anyType{}; SuperID=anyType{}; EmpNombre=anyType{}; EmpApellido=anyType{}; EmployeeTipo=anyType{}; ZonaID=anyType{}; }
                        Data.RegID = Integer.valueOf(ic.getProperty(0).toString().trim());


                        if(Data.RegID==-10)
                        {
                            // Password is incorrect
                            Success = ERROR_FAILEDLOGIN;
                        } else
                        {
                            // Password is Correct so let's continuie
                            Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                            Data.EmployeeID = Integer.valueOf(ic.getProperty(2).toString().trim());
                            Data.MobUser = ic.getProperty(3).toString().trim();
                            Data.MobPass = ic.getProperty(4).toString().trim();
                            Data.MobActive = Integer.valueOf(ic.getProperty(5).toString().trim());

                            myUser.saveRecord(Data);
                            Success = SUCCESSFUL_LOGIN;
                        }

                    } // The For Ends here


                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

        return Success;
    }


    public void getClientAsignados (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getClientAsignados");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getClientAsignados", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ClientAsignados myclienAsi = new SQL_ClientAsignados(_Cont);
                Data_ClientAsignados Data = new Data_ClientAsignados();


                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);

                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{RegID=-10; MasterID=anyType{}; ClientID=anyType{}; EmployeeID=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.RegID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.ClientID = Integer.valueOf(ic.getProperty(2).toString().trim());
                        Data.EmployeeID = Integer.valueOf(ic.getProperty(3).toString().trim());

                        myclienAsi.saveRecord(Data);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getTipoAddresses (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getTipoAddresses");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getTipoAddresses", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_TipoAddresses myTipAdre = new SQL_TipoAddresses(_Cont);
                Data_TipoAddresses Data = new Data_TipoAddresses();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {

                    SoapObject root_ic = (SoapObject)resSoap.getProperty(0);


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {

                        //String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.TipoAddress = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.Descripcion = ic.getProperty(1).toString().trim();

                        myTipAdre.saveRecord(Data);

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getClientAddress (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getClientAddress");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getClientAddress", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ClientAddress myClienAdre = new SQL_ClientAddress(_Cont);
                Data_ClientAddress Data = new Data_ClientAddress();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {

                        //anyType{item=anyType{ClientAddressID=-10; MasterID=anyType{}; ClientID=anyType{}; ClientDireccion=anyType{}; ClientLongitud=anyType{}; ClientLatitud=anyType{}; TipoAddress=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.ClientAddressID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.ClientID = Integer.valueOf(ic.getProperty(2).toString().trim());
                        Data.ClientDireccion = ic.getProperty(3).toString().trim();
                        Data.ClientLongitud = ic.getProperty(4).toString().trim();
                        Data.ClientLatitud = ic.getProperty(5).toString().trim();
                        Data.TipoAddress = Integer.valueOf(ic.getProperty(6).toString().trim());

                        myClienAdre.saveRecord(Data);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getContactoTipos (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getContactoTipos");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getContactoTipos", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ContactoTipos myCon = new SQL_ContactoTipos(_Cont);
                Data_ContactoTipos Data = new Data_ContactoTipos();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{TipoContacto=-10; Descripcion=anyType{}; }; }
                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);


                        Data.TipoContacto = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.Descripcion = ic.getProperty(1).toString().trim();

                        myCon.saveRecord(Data);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getClienteContacto (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getClienteContacto");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getClienteContacto", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ClienteContacto myCon = new SQL_ClienteContacto(_Cont);
                Data_ClienteContacto Data = new Data_ClienteContacto();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{ClientContactID=-10; MasterID=anyType{}; ClientID=anyType{}; TipoContacto=anyType{}; NumeroTel=anyType{}; ClientCorreo=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.ClientContactID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.ClientID = Integer.valueOf(ic.getProperty(2).toString().trim());
                        Data.TipoContacto = Integer.valueOf(ic.getProperty(3).toString().trim());
                        Data.NumeroTel = ic.getProperty(4).toString().trim();
                        Data.ClientCorreo = ic.getProperty(5).toString().trim();


                        myCon.saveRecord(Data);



                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }

    public void getClientTipos (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getClientTipos");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getClientTipos", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ClientTipos myCon = new SQL_ClientTipos(_Cont);
                Data_ClientTipos Data = new Data_ClientTipos();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{ClientTipo=-10; Descripcion=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.ClientTipo = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.Descripcion = ic.getProperty(1).toString().trim();

                        myCon.saveRecord(Data);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getClasificacion (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getClasificacion");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getClasificacion", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();
                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getClienteMaestro (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getClienteMaestro");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getClienteMaestro", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ClienteMaestro myCon = new SQL_ClienteMaestro(_Cont);
                Data_ClienteMaestro Data = new Data_ClienteMaestro();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);


                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{ClientID=-10; MasterID=anyType{}; ClientCode=anyType{}; ClientTipo=anyType{}; ClientNombre=anyType{}; ClientApellido=anyType{}; ClientContactID=anyType{}; ClientAddressID=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);


                        Data.ClientID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.ClientCode = ic.getProperty(2).toString().trim();
                        Data.ClientTipo = Integer.valueOf(ic.getProperty(3).toString().trim());
                        Data.ClientNombre = ic.getProperty(4).toString().trim();
                        Data.ClientApellido = ic.getProperty(5).toString().trim();
                        Data.ClientContactID = Integer.valueOf(ic.getProperty(6).toString().trim());
                        Data.ClientAddressID = Integer.valueOf(ic.getProperty(7).toString().trim());


                        //Data.Clasificacion = ic.getProperty(0).toString().trim();


                        myCon.saveRecord(Data);

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getEditsIntegrity (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getEditsIntegrity");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getEditsIntegrity", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();
                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getEdits (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getEdits");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getEdits", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();
                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }



    public void getIntegrityFull (String user, String pass, String Tabla, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getIntegrityFull");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("Tabla", Tabla);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getIntegrityFull", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();
                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {

                        String flagAnswer = resSoap.getProperty(i).toString();
                        SoapObject  ic = (SoapObject)resSoap.getProperty(i);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }



    public void getEspecialidades (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getEspecialidades");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getEspecialidades", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_Especialidades myCon = new SQL_Especialidades(_Cont);
                Data_Especialidades Data = new Data_Especialidades();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{ClientContactID=-10; MasterID=anyType{}; ClientID=anyType{}; TipoContacto=anyType{}; NumeroTel=anyType{}; ClientCorreo=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.EspecID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.Descripcion = ic.getProperty(2).toString().trim();


                        myCon.saveRecord(Data);



                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }


    public void getClienteDatosZ (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getClienteDatosZ");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getClienteDatosZ", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_ClienteDatosZ myCon = new SQL_ClienteDatosZ(_Cont);
                Data_ClienteDatosZ Data = new Data_ClienteDatosZ();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{ClientContactID=-10; MasterID=anyType{}; ClientID=anyType{}; TipoContacto=anyType{}; NumeroTel=anyType{}; ClientCorreo=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.RegID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.ClientID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(2).toString().trim());
                        Data.ZonaID = Integer.valueOf(ic.getProperty(3).toString().trim());
                        Data.EspecID = Integer.valueOf(ic.getProperty(4).toString().trim());
                        Data.Clasificacion = ic.getProperty(5).toString().trim();



                        myCon.saveRecord(Data);



                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }



    public void getParrilla (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getParrilla");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getParrilla", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_Parrilla myCon = new SQL_Parrilla(_Cont);
                Data_Parrilla Data = new Data_Parrilla();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{ClientContactID=-10; MasterID=anyType{}; ClientID=anyType{}; TipoContacto=anyType{}; NumeroTel=anyType{}; ClientCorreo=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.RegID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.PlanID = Integer.valueOf(ic.getProperty(2).toString().trim());
                        Data.EspecID = Integer.valueOf(ic.getProperty(3).toString().trim());
                        Data.ProductID = Integer.valueOf(ic.getProperty(4).toString().trim());
                        Data.SugA = ic.getProperty(5).toString().trim();
                        Data.SugB = ic.getProperty(6).toString().trim();
                        Data.SugC = ic.getProperty(7).toString().trim();


                        myCon.saveRecord(Data);



                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }



    public void getProductos (String user, String pass, int ciclo, Context _Cont){

        // Making the soap request object with its parameters
        SoapObject request = new SoapObject(Configuracion.WS_NAMESPACE, "getProductos");

        // Creating all the properties required by the service
        request.addProperty("user", user);
        request.addProperty("pass", pass);
        request.addProperty("ciclo", ciclo);

        // Creating the envelope of the soap request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        // Creating transport method
        HttpTransportSE transporte = new HttpTransportSE(Configuracion.UrlWSDL);
        transporte.debug = true;

        // the actual soap call
        try
        {
            try {
                transporte.call("urn:EZSoap/getProductos", envelope);
                // Assigning the response of the server to a soap object
                //SoapObject resSoap =(SoapObject) envelope.getResponse(); //either .bodyIn or use .getResponse();

                SQL_Productos myCon = new SQL_Productos(_Cont);
                Data_Productos Data = new Data_Productos();

                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                // Getting root element of the soap response (responseMsg)
                SoapObject root_ic = (SoapObject)resSoap.getProperty(0);
                try {


                    for (int i = 0; i < root_ic.getPropertyCount(); i++)
                    {
                        //anyType{item=anyType{ClientContactID=-10; MasterID=anyType{}; ClientID=anyType{}; TipoContacto=anyType{}; NumeroTel=anyType{}; ClientCorreo=anyType{}; }; }

                        SoapObject  ic = (SoapObject)root_ic.getProperty(i);

                        Data.ProductID = Integer.valueOf(ic.getProperty(0).toString().trim());
                        Data.MasterID = Integer.valueOf(ic.getProperty(1).toString().trim());
                        Data.EspecID = Integer.valueOf(ic.getProperty(2).toString().trim());
                        Data.Nombre = ic.getProperty(3).toString().trim();



                        myCon.saveRecord(Data);



                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(XmlPullParserException e)
        {
        }

    }

}
