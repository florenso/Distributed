
package calcws.jaxws;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "add", namespace = "http://calcws/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "add", namespace = "http://calcws/", propOrder = {
    "a",
    "b"
})
public class Add {

    @XmlElement(name = "a", namespace = "")
    private BigInteger a;
    @XmlElement(name = "b", namespace = "")
    private BigInteger b;

    /**
     * 
     * @return
     *     returns BigInteger
     */
    public BigInteger getA() {
        return this.a;
    }

    /**
     * 
     * @param a
     *     the value for the a property
     */
    public void setA(BigInteger a) {
        this.a = a;
    }

    /**
     * 
     * @return
     *     returns BigInteger
     */
    public BigInteger getB() {
        return this.b;
    }

    /**
     * 
     * @param b
     *     the value for the b property
     */
    public void setB(BigInteger b) {
        this.b = b;
    }

}
