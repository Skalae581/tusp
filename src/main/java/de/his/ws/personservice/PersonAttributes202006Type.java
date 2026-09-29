
package de.his.ws.personservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für personAttributes202006Type complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="personAttributes202006Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personAttribute202006" type="{http://www.his.de/ws/PersonService}PersonAttribute202006" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personAttributes202006Type", propOrder = {
    "personAttribute202006"
})
public class PersonAttributes202006Type {

    protected List<PersonAttribute202006> personAttribute202006;

    /**
     * Gets the value of the personAttribute202006 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personAttribute202006 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonAttribute202006().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonAttribute202006 }
     * 
     * 
     */
    public List<PersonAttribute202006> getPersonAttribute202006() {
        if (personAttribute202006 == null) {
            personAttribute202006 = new ArrayList<PersonAttribute202006>();
        }
        return this.personAttribute202006;
    }

}
