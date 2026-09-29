
package de.his.ws.studentservice;

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
 *         &lt;element name="EntranceQualifications" type="{http://www.his.de/ws/StudentService}EntranceQualificationsType"/&gt;
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
    "entranceQualifications"
})
@XmlRootElement(name = "findHzbByPersonIdResponse")
public class FindHzbByPersonIdResponse {

    @XmlElement(name = "EntranceQualifications", required = true)
    protected EntranceQualificationsType entranceQualifications;

    /**
     * Ruft den Wert der entranceQualifications-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EntranceQualificationsType }
     *     
     */
    public EntranceQualificationsType getEntranceQualifications() {
        return entranceQualifications;
    }

    /**
     * Legt den Wert der entranceQualifications-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EntranceQualificationsType }
     *     
     */
    public void setEntranceQualifications(EntranceQualificationsType value) {
        this.entranceQualifications = value;
    }

}
