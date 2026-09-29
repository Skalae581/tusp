
package de.his.ws.appointmentseriesservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="planelementId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="periodId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="rhythmId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="weekDayId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="firstAppointment" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="lastAppointment" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
 *         &lt;element name="academicTimeSpecificationId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="expectedAttendees" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="notice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "planelementId",
    "periodId",
    "rhythmId",
    "weekDayId",
    "firstAppointment",
    "lastAppointment",
    "startTime",
    "endTime",
    "academicTimeSpecificationId",
    "expectedAttendees",
    "notice"
})
@XmlRootElement(name = "createAppointmentSeries")
public class CreateAppointmentSeries {

    protected long planelementId;
    protected long periodId;
    protected long rhythmId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long weekDayId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstAppointment;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastAppointment;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar startTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar endTime;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long academicTimeSpecificationId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger expectedAttendees;
    @XmlElement(required = true, nillable = true)
    protected String notice;

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
     * Ruft den Wert der periodId-Eigenschaft ab.
     * 
     */
    public long getPeriodId() {
        return periodId;
    }

    /**
     * Legt den Wert der periodId-Eigenschaft fest.
     * 
     */
    public void setPeriodId(long value) {
        this.periodId = value;
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
     * Ruft den Wert der weekDayId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getWeekDayId() {
        return weekDayId;
    }

    /**
     * Legt den Wert der weekDayId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setWeekDayId(Long value) {
        this.weekDayId = value;
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
     * Ruft den Wert der startTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Legt den Wert der startTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Ruft den Wert der endTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Legt den Wert der endTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Ruft den Wert der academicTimeSpecificationId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAcademicTimeSpecificationId() {
        return academicTimeSpecificationId;
    }

    /**
     * Legt den Wert der academicTimeSpecificationId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAcademicTimeSpecificationId(Long value) {
        this.academicTimeSpecificationId = value;
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

}
