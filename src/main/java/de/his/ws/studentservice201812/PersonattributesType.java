
package de.his.ws.studentservice201812;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für personattributesType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="personattributesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personattribute" type="{http://www.his.de/ws/StudentService201812}PersonAttribute" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personattributesType", propOrder = {
    "personattribute"
})
public class PersonattributesType {

    protected List<PersonAttribute> personattribute;

    /**
     * Gets the value of the personattribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personattribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonattribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonAttribute }
     * 
     * 
     */
    public List<PersonAttribute> getPersonattribute() {
        if (personattribute == null) {
            personattribute = new ArrayList<PersonAttribute>();
        }
        return this.personattribute;
    }

}
