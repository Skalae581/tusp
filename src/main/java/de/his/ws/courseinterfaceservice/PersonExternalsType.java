
package de.his.ws.courseinterfaceservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für personExternalsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="personExternalsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personExternal" type="{http://www.his.de/ws/CourseInterfaceService}PersonExternal" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personExternalsType", propOrder = {
    "personExternal"
})
public class PersonExternalsType {

    protected List<PersonExternal> personExternal;

    /**
     * Gets the value of the personExternal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personExternal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonExternal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonExternal }
     * 
     * 
     */
    public List<PersonExternal> getPersonExternal() {
        if (personExternal == null) {
            personExternal = new ArrayList<PersonExternal>();
        }
        return this.personExternal;
    }

}
