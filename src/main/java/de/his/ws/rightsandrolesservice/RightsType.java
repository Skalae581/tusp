
package de.his.ws.rightsandrolesservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für rightsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="rightsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rightsInRole" type="{http://www.his.de/ws/RightsAndRolesService}RightsInRole" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rightsType", propOrder = {
    "rightsInRole"
})
public class RightsType {

    protected List<RightsInRole> rightsInRole;

    /**
     * Gets the value of the rightsInRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rightsInRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRightsInRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RightsInRole }
     * 
     * 
     */
    public List<RightsInRole> getRightsInRole() {
        if (rightsInRole == null) {
            rightsInRole = new ArrayList<RightsInRole>();
        }
        return this.rightsInRole;
    }

}
