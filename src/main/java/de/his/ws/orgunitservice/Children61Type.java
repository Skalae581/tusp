
package de.his.ws.orgunitservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für children61Type complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="children61Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="orgunit61" type="{http://www.his.de/ws/OrgUnitService}Orgunit61" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "children61Type", propOrder = {
    "orgunit61"
})
public class Children61Type {

    protected List<Orgunit61> orgunit61;

    /**
     * Gets the value of the orgunit61 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orgunit61 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrgunit61().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Orgunit61 }
     * 
     * 
     */
    public List<Orgunit61> getOrgunit61() {
        if (orgunit61 == null) {
            orgunit61 = new ArrayList<Orgunit61>();
        }
        return this.orgunit61;
    }

}
