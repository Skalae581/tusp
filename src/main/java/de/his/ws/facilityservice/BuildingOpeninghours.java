
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für BuildingOpeninghours complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="BuildingOpeninghours"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="closed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="notice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="weekdays" type="{http://www.his.de/ws/FacilityService}weekdaysType" minOccurs="0"/&gt;
 *         &lt;element name="holidays" type="{http://www.his.de/ws/FacilityService}OpeninghoursHoliday" minOccurs="0"/&gt;
 *         &lt;element name="exceptions" type="{http://www.his.de/ws/FacilityService}exceptionsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BuildingOpeninghours", propOrder = {
    "id",
    "closed",
    "notice",
    "from",
    "to",
    "weekdays",
    "holidays",
    "exceptions"
})
public class BuildingOpeninghours {

    @XmlElement(nillable = true)
    protected Long id;
    protected boolean closed;
    @XmlElement(nillable = true)
    protected String notice;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar from;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar to;
    protected WeekdaysType weekdays;
    @XmlElement(nillable = true)
    protected OpeninghoursHoliday holidays;
    protected ExceptionsType exceptions;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der closed-Eigenschaft ab.
     * 
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * Legt den Wert der closed-Eigenschaft fest.
     * 
     */
    public void setClosed(boolean value) {
        this.closed = value;
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
     * Ruft den Wert der from-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrom() {
        return from;
    }

    /**
     * Legt den Wert der from-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrom(XMLGregorianCalendar value) {
        this.from = value;
    }

    /**
     * Ruft den Wert der to-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTo() {
        return to;
    }

    /**
     * Legt den Wert der to-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTo(XMLGregorianCalendar value) {
        this.to = value;
    }

    /**
     * Ruft den Wert der weekdays-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link WeekdaysType }
     *     
     */
    public WeekdaysType getWeekdays() {
        return weekdays;
    }

    /**
     * Legt den Wert der weekdays-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link WeekdaysType }
     *     
     */
    public void setWeekdays(WeekdaysType value) {
        this.weekdays = value;
    }

    /**
     * Ruft den Wert der holidays-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OpeninghoursHoliday }
     *     
     */
    public OpeninghoursHoliday getHolidays() {
        return holidays;
    }

    /**
     * Legt den Wert der holidays-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OpeninghoursHoliday }
     *     
     */
    public void setHolidays(OpeninghoursHoliday value) {
        this.holidays = value;
    }

    /**
     * Ruft den Wert der exceptions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExceptionsType }
     *     
     */
    public ExceptionsType getExceptions() {
        return exceptions;
    }

    /**
     * Legt den Wert der exceptions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExceptionsType }
     *     
     */
    public void setExceptions(ExceptionsType value) {
        this.exceptions = value;
    }

}
