
package de.his.ws.appointmentseriesservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Zeitspanne eines Einzeltermins oder einer Terminserie
 * 
 * <p>Java-Klasse für AppointmentTime complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AppointmentTime"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="academicTimeSpecificationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentTime", propOrder = {
    "start",
    "end",
    "academicTimeSpecificationId"
})
public class AppointmentTime {

    @XmlElement(required = true, nillable = true)
    protected Object start;
    @XmlElement(required = true, nillable = true)
    protected Object end;
    @XmlElement(nillable = true)
    protected Long academicTimeSpecificationId;

    /**
     * Ruft den Wert der start-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getStart() {
        return start;
    }

    /**
     * Legt den Wert der start-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setStart(Object value) {
        this.start = value;
    }

    /**
     * Ruft den Wert der end-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getEnd() {
        return end;
    }

    /**
     * Legt den Wert der end-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setEnd(Object value) {
        this.end = value;
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

}
