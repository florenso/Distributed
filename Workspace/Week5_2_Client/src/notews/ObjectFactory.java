
package notews;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the calcws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Add_QNAME = new QName("http://notews/", "addNote");
    private final static QName _AddResponse_QNAME = new QName("http://notews/", "addNoteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: notews
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddNoteResponse }
     * 
     */
    public AddNoteResponse createAddResponse() {
        return new AddNoteResponse();
    }

    /**
     * Create an instance of {@link AddNote }
     * 
     */
    public AddNote createAdd() {
        return new AddNote();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notews/", name = "addNote")
    public JAXBElement<AddNote> createAdd(AddNote value) {
        return new JAXBElement<AddNote>(_Add_QNAME, AddNote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNoteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://notews/", name = "addNoteResponse")
    public JAXBElement<AddNoteResponse> createAddResponse(AddNoteResponse value) {
        return new JAXBElement<AddNoteResponse>(_AddResponse_QNAME, AddNoteResponse.class, null, value);
    }

}
