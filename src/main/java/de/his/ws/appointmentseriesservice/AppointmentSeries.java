
package de.his.ws.appointmentseriesservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Terminserie (PlannedDates)
 * 
 * <p>Java-Klasse für AppointmentSeries complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AppointmentSeries"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="plannedDatesId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="planelementId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="rhythmId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="weekdayId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="firstAppointment" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="lastAppointment" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="time" type="{http://www.his.de/ws/AppointmentSeriesService}AppointmentTime" minOccurs="0"/&gt;
 *         &lt;element name="expectedAttendees" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="notice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="defaultRoomId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentSeries", propOrder = {
    "plannedDatesId",
    "planelementId",
    "rhythmId",
    "weekdayId",
    "firstAppointment",
    "lastAppointment",
    "time",
    "expectedAttendees",
    "notice",
    "defaultRoomId"
})
public class AppointmentSeries {

    protected long plannedDatesId;
    protected long planelementId;
    @XmlElement(nillable = true)
    protected Long rhythmId;
    @XmlElement(nillable = true)
    protected Long weekdayId;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstAppointment;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastAppointment;
    @XmlElement(nillable = true)
    protected AppointmentTime time;
    @XmlElement(nillable = true)
    protected BigInteger expectedAttendees;
    @XmlElement(nillable = true)
    protected String notice;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long defaultRoomId;

    /**
     * Ruft den Wert der plannedDatesId-Eigenschaft ab.
     * 
     */
    public long getPlannedDatesId() {
        return plannedDatesId;
    }

    /**
     * Legt den Wert der plannedDatesId-Eigenschaft fest.
     * 
     */
    public void setPlannedDatesId(long value) {
        this.plannedDatesId = value;
    }

    /**
     * Ruft den Wert der planelementId-Eigenschaft ab.
     * 
     */
    public long getPlanelementId() {
        return planelementId;
    }

    /**
     * Legt den Wert der planelementId-Eigenschaft fest.
     * 
     */
    public void setPlanelementId(long value) {
        this.planelementId = value;
    }

    /**
     * Ruft den Wert der rhythmId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRhythmId() {
        return rhythmId;
    }

    /**
     * Legt den Wert der rhythmId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRhythmId(Long value) {
        this.rhythmId = value;
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
     * Ruft den Wert der firstAppointment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstAppointment() {
        return firstAppointment;
    }

    /**
     * Legt den Wert der firstAppointment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstAppointment(XMLGregorianCalendar value) {
        this.firstAppointment = value;
    }

    /**
     * Ruft den Wert der lastAppointment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastAppointment() {
        return lastAppointment;
    }

    /**
     * Legt den Wert der lastAppointment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastAppointment(XMLGregorianCalendar value) {
        this.lastAppointment = value;
    }

    /**
     * Ruft den Wert der time-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AppointmentTime }
     *     
     */
    public AppointmentTime getTime() {
        return time;
    }

    /**
     * Legt den Wert der time-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentTime }
     *     
     */
    public void setTime(AppointmentTime value) {
        this.time = value;
    }

    /**
     * Ruft den Wert der expectedAttendees-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpectedAttendees() {
        return expectedAttendees;
    }

    /**
     * Legt den Wert der expectedAttendees-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpectedAttendees(BigInteger value) {
        this.expectedAttendees = value;
    }

    /**
     * Ruft den Wert der notice-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotice() {
        return notice;
    }

    /**
     * Legt den Wert der notice-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotice(String value) {
        this.notice = value;
    }

    /**
     * Ruft den Wert der defaultRoomId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDefaultRoomId() {
        return defaultRoomId;
    }

    /**
     * Legt den Wert der defaultRoomId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDefaultRoomId(Long value) {
        this.defaultRoomId = value;
    }

}
