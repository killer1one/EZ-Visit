package Sqlite_Data;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import Util.Configuracion;

/**
 * Created by Ec_Colon on 23/2/15.
 */
public class Sync {


    public void getDeliverEdits (String user, String pass, int ciclo){

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
                transporte.call("urn:EZSoap#getDeliverEdits", envelope);
                // Assigning the response of the server to a soap object
                SoapObject resSoap =(SoapObject) envelope.bodyIn;
                try {


                    for (int i = 0; i < resSoap.getPropertyCount(); i++)
                    {
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

}
