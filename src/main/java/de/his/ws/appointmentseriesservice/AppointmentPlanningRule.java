
package de.his.ws.appointmentseriesservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für AppointmentPlanningRule complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AppointmentPlanningRule"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="rhythmId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="weekdayId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="earliestAppointment" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="latestAppointment" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="earliestBegin" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="latestEnd" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="roomPreferences" type="{http://www.his.de/ws/AppointmentSeriesService}roomPreferencesType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentPlanningRule", propOrder = {
    "id",
    "quantity",
    "rhythmId",
    "duration",
    "weekdayId",
    "earliestAppointment",
    "latestAppointment",
    "earliestBegin",
    "latestEnd",
    "roomPreferences"
})
public class AppointmentPlanningRule {

    protected long id;
    @XmlElement(required = true)
    protected BigInteger quantity;
    protected long rhythmId;
    @XmlElement(required = true)
    protected BigInteger duration;
    @XmlElement(nillable = true)
    protected Long weekdayId;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar earliestAppointment;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar latestAppointment;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar earliestBegin;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar latestEnd;
    @XmlElement(nillable = true)
    protected RoomPreferencesType roomPreferences;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der quantity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantity() {
        return quantity;
    }

    /**
     * Legt den Wert der quantity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantity(BigInteger value) {
        this.quantity = value;
    }

    /**
     * Ruft den Wert der rhythmId-Eigenschaft ab.
     * 
     */
    public long getRhythmId() {
        return rhythmId;
    }

    /**
     * Legt den Wert der rhythmId-Eigenschaft fest.
     * 
     */
    public void setRhythmId(long value) {
        this.rhythmId = value;
    }

    /**
     * Ruft den Wert der duration-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDuration() {
        return duration;
    }

    /**
     * Legt den Wert der duration-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDuration(BigInteger value) {
        this.duration = value;
    }

    /**
     * Ruft den Wert der weekdayId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getWeekdayId() {
        return weekdayId;
    }

    /**
     * Legt den Wert der weekdayId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setWeekdayId(Long value) {
        this.weekdayId = value;
    }

    /**
     * Ruft den Wert der earliestAppointment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEarliestAppointment() {
        return earliestAppointment;
    }

    /**
     * Legt den Wert der earliestAppointment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEarliestAppointment(XMLGregorianCalendar value) {
        this.earliestAppointment = value;
    }

    /**
     * Ruft den Wert der latestAppointment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestAppointment() {
        return latestAppointment;
    }

    /**
     * Legt den Wert der latestAppointment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestAppointment(XMLGregorianCalendar value) {
        this.latestAppointment = value;
    }

    /**
     * Ruft den Wert der earliestBegin-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEarliestBegin() {
        return earliestBegin;
    }

    /**
     * Legt den Wert der earliestBegin-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEarliestBegin(XMLGregorianCalendar value) {
        this.earliestBegin = value;
    }

    /**
     * Ruft den Wert der latestEnd-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLatestEnd() {
        return latestEnd;
    }

    /**
     * Legt den Wert der latestEnd-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLatestEnd(XMLGregorianCalendar value) {
        this.latestEnd = value;
    }

    /**
     * Ruft den Wert der roomPreferences-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomPreferencesType }
     *     
     */
    public RoomPreferencesType getRoomPreferences() {
        return roomPreferences;
    }

    /**
     * Legt den Wert der roomPreferences-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomPreferencesType }
     *     
     */
    public void setRoomPreferences(RoomPreferencesType value) {
        this.roomPreferences = value;
    }

}
