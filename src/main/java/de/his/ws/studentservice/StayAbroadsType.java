
package de.his.ws.studentservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für stayAbroadsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="stayAbroadsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="stayAbroad" type="{http://www.his.de/ws/StudentService}StayAbroadDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stayAbroadsType", propOrder = {
    "stayAbroad"
})
public class StayAbroadsType {

    @XmlElement(nillable = true)
    protected List<StayAbroadDto> stayAbroad;

    /**
     * Gets the value of the stayAbroad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stayAbroad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStayAbroad().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StayAbroadDto }
     * 
     * 
     */
    public List<StayAbroadDto> getStayAbroad() {
        if (stayAbroad == null) {
            stayAbroad = new ArrayList<StayAbroadDto>();
        }
        return this.stayAbroad;
    }

}
