
package calcws.jaxws;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addResponse", namespace = "http://calcws/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addResponse", namespace = "http://calcws/")
public class AddResponse {

    @XmlElement(name = "return", namespace = "")
    private BigInteger _return;

    /**
     * 
     * @return
     *     returns BigInteger
     */
    public BigInteger getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(BigInteger _return) {
        this._return = _return;
    }

}
