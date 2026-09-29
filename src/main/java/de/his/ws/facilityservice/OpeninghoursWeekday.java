
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für OpeninghoursWeekday complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="OpeninghoursWeekday"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="monday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="tuesday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="wednesday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="thursday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="friday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="saturday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="sunday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
@XmlType(name = "OpeninghoursWeekday", propOrder = {
    "monday",
    "tuesday",
    "wednesday",
    "thursday",
    "friday",
    "saturday",
    "sunday",
    "status",
    "timeslots"
})
public class OpeninghoursWeekday {

    protected Boolean monday;
    protected Boolean tuesday;
    protected Boolean wednesday;
    protected Boolean thursday;
    protected Boolean friday;
    protected Boolean saturday;
    protected Boolean sunday;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected StatusType status;
    protected TimeslotsType timeslots;

    /**
     * Ruft den Wert der monday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMonday() {
        return monday;
    }

    /**
     * Legt den Wert der monday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMonday(Boolean value) {
        this.monday = value;
    }

    /**
     * Ruft den Wert der tuesday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTuesday() {
        return tuesday;
    }

    /**
     * Legt den Wert der tuesday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTuesday(Boolean value) {
        this.tuesday = value;
    }

    /**
     * Ruft den Wert der wednesday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWednesday() {
        return wednesday;
    }

    /**
     * Legt den Wert der wednesday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWednesday(Boolean value) {
        this.wednesday = value;
    }

    /**
     * Ruft den Wert der thursday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isThursday() {
        return thursday;
    }

    /**
     * Legt den Wert der thursday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setThursday(Boolean value) {
        this.thursday = value;
    }

    /**
     * Ruft den Wert der friday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFriday() {
        return friday;
    }

    /**
     * Legt den Wert der friday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFriday(Boolean value) {
        this.friday = value;
    }

    /**
     * Ruft den Wert der saturday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSaturday() {
        return saturday;
    }

    /**
     * Legt den Wert der saturday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSaturday(Boolean value) {
        this.saturday = value;
    }

    /**
     * Ruft den Wert der sunday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSunday() {
        return sunday;
    }

    /**
     * Legt den Wert der sunday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSunday(Boolean value) {
        this.sunday = value;
    }

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
