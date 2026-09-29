
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für OpeninghoursHoliday complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="OpeninghoursHoliday"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="status" type="{http://www.his.de/ws/FacilityService}statusType"/&gt;
 *         &lt;element name="timeslots" type="{http://www.his.de/ws/FacilityService}timeslotsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OpeninghoursHoliday", propOrder = {
    "status",
    "timeslots"
})
public class OpeninghoursHoliday {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected StatusType status;
    protected TimeslotsType timeslots;

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

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
