
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
 *         &lt;element name="timeslots" type="{http://www.his.de/ws/FacilityService}timeslotsType"/&gt;
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
    "timeslots"
})
@XmlRootElement(name = "readBuildingOpeninghoursForDateResponse")
public class ReadBuildingOpeninghoursForDateResponse {

    @XmlElement(required = true)
    protected TimeslotsType timeslots;

    /**
     * Ruft den Wert der timeslots-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TimeslotsType }
     *     
     */
    public TimeslotsType getTimeslots() {
        return timeslots;
    }

    /**
     * Legt den Wert der timeslots-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeslotsType }
     *     
     */
    public void setTimeslots(TimeslotsType value) {
        this.timeslots = value;
    }

}
