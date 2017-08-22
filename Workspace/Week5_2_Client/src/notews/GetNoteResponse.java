package notews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "getNoteResponse", propOrder = {
	    "_return"
	})
	public class GetNoteResponse {

	    @XmlElement(name = "return")
	    protected boolean _return;

	    /**
	     * Gets the value of the return property.
	     * 
	     */
	    public boolean isReturn() {
	        return _return;
	    }

	    /**
	     * Sets the value of the return property.
	     * 
	     */
	    public void setReturn(boolean value) {
	        this._return = value;
	    }
	}

