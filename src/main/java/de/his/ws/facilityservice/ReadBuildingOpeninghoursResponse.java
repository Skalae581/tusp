
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
 *         &lt;element name="openinghours" type="{http://www.his.de/ws/FacilityService}openinghoursType"/&gt;
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
    "openinghours"
})
@XmlRootElement(name = "readBuildingOpeninghoursResponse")
public class ReadBuildingOpeninghoursResponse {

    @XmlElement(required = true)
    protected OpeninghoursType openinghours;

    /**
     * Ruft den Wert der openinghours-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OpeninghoursType }
     *     
     */
    public OpeninghoursType getOpeninghours() {
        return openinghours;
    }

    /**
     * Legt den Wert der openinghours-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OpeninghoursType }
     *     
     */
    public void setOpeninghours(OpeninghoursType value) {
        this.openinghours = value;
    }

}
