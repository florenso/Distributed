
package notews;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "NoteService", targetNamespace = "http://notews/", wsdlLocation = "http://localhost:80/Week5_2_Server/note?wsdl")
public class NoteService
    extends Service
{

    private final static URL NOTESERVICE_WSDL_LOCATION;
    private final static WebServiceException NOTESERVICE_EXCEPTION;
    private final static QName NOTESERVICE_QNAME = new QName("http://notews/", "NoteServerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:80/Week5_2_Server/note?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        NOTESERVICE_WSDL_LOCATION = url;
        NOTESERVICE_EXCEPTION = e;
    }

    public NoteService() {
        super(__getWsdlLocation(), NOTESERVICE_QNAME);
    }

    public NoteService(WebServiceFeature... features) {
        super(__getWsdlLocation(), NOTESERVICE_QNAME, features);
    }

    public NoteService(URL wsdlLocation) {
        super(wsdlLocation, NOTESERVICE_QNAME);
    }

    public NoteService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, NOTESERVICE_QNAME, features);
    }

    public NoteService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NoteService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Calculator
     */
    @WebEndpoint(name = "NotePort")
    public Notes getNotePort() {
        return super.getPort(new QName("http://notews/", "NoteServerPort"), Notes.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Calculator
     */
    @WebEndpoint(name = "NotePort")
    public Notes getNotePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://notews/", "NoteServerPort"), Notes.class, features);
    }

    private static URL __getWsdlLocation() {
        if (NOTESERVICE_EXCEPTION!= null) {
            throw NOTESERVICE_EXCEPTION;
        }
        return NOTESERVICE_WSDL_LOCATION;
    }

}
