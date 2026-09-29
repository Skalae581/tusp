
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="campus" type="{http://www.his.de/ws/FacilityService}Campus202106"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "campus"
})
@XmlRootElement(name = "saveCampus202106")
public class SaveCampus202106 {

    @XmlElement(required = true)
    protected Campus202106 campus;

    /**
     * Ruft den Wert der campus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Campus202106 }
     *     
     */
    public Campus202106 getCampus() {
        return campus;
    }

    /**
     * Legt den Wert der campus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Campus202106 }
     *     
     */
    public void setCampus(Campus202106 value) {
        this.campus = value;
    }

}
