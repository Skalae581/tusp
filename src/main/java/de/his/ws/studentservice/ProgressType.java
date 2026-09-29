
package de.his.ws.studentservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für progressType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="progressType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="degreeProgramProgress" type="{http://www.his.de/ws/StudentService}DegreeProgramProgressDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "progressType", propOrder = {
    "degreeProgramProgress"
})
public class ProgressType {

    protected List<DegreeProgramProgressDto> degreeProgramProgress;

    /**
     * Gets the value of the degreeProgramProgress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the degreeProgramProgress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDegreeProgramProgress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DegreeProgramProgressDto }
     * 
     * 
     */
    public List<DegreeProgramProgressDto> getDegreeProgramProgress() {
        if (degreeProgramProgress == null) {
            degreeProgramProgress = new ArrayList<DegreeProgramProgressDto>();
        }
        return this.degreeProgramProgress;
    }

}
