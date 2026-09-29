
package de.his.ws.studentservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EntranceQualifications201712Type complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EntranceQualifications201712Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entrancequalification" type="{http://www.his.de/ws/StudentService}EntranceQualification201712" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntranceQualifications201712Type", propOrder = {
    "entrancequalification"
})
public class EntranceQualifications201712Type {

    protected List<EntranceQualification201712> entrancequalification;

    /**
     * Gets the value of the entrancequalification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entrancequalification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntrancequalification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntranceQualification201712 }
     * 
     * 
     */
    public List<EntranceQualification201712> getEntrancequalification() {
        if (entrancequalification == null) {
            entrancequalification = new ArrayList<EntranceQualification201712>();
        }
        return this.entrancequalification;
    }

}
