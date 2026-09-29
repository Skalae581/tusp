
package de.his.ws.studentservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für progress70Type complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="progress70Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="degreeProgramProgress70" type="{http://www.his.de/ws/StudentService}DegreeProgramProgressDto70" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "progress70Type", propOrder = {
    "degreeProgramProgress70"
})
public class Progress70Type {

    protected List<DegreeProgramProgressDto70> degreeProgramProgress70;

    /**
     * Gets the value of the degreeProgramProgress70 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the degreeProgramProgress70 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDegreeProgramProgress70().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DegreeProgramProgressDto70 }
     * 
     * 
     */
    public List<DegreeProgramProgressDto70> getDegreeProgramProgress70() {
        if (degreeProgramProgress70 == null) {
            degreeProgramProgress70 = new ArrayList<DegreeProgramProgressDto70>();
        }
        return this.degreeProgramProgress70;
    }

}
