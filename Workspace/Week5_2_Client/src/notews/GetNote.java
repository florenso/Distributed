package notews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNote", propOrder = {
    "id"
})

public class GetNote {
	protected int id;
	
	public int getId(){
		return id;
	}
	
	public void setId(int value){
		this.id = value;
	}
}
