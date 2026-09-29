
package de.his.ws.appointmentseriesservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Durchführende Dozentin/durchführender Dozent
 * 
 * <p>Java-Klasse für InstructingLecturer complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="InstructingLecturer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="instructorId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="isPerson" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isPlaceholder" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="person" type="{http://www.his.de/ws/AppointmentSeriesService}Lecturer" minOccurs="0"/&gt;
 *         &lt;element name="placeholder" type="{http://www.his.de/ws/AppointmentSeriesService}Lecturer" minOccurs="0"/&gt;
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstructingLecturer", propOrder = {
    "instructorId",
    "isPerson",
    "isPlaceholder",
    "person",
    "placeholder",
    "position"
})
public class InstructingLecturer {

    protected long instructorId;
    protected boolean isPerson;
    protected boolean isPlaceholder;
    @XmlElement(nillable = true)
    protected Lecturer person;
    @XmlElement(nillable = true)
    protected Lecturer placeholder;
    @XmlElement(nillable = true)
    protected BigInteger position;

    /**
     * Ruft den Wert der instructorId-Eigenschaft ab.
     * 
     */
    public long getInstructorId() {
        return instructorId;
    }

    /**
     * Legt den Wert der instructorId-Eigenschaft fest.
     * 
     */
    public void setInstructorId(long value) {
        this.instructorId = value;
    }

    /**
     * Ruft den Wert der isPerson-Eigenschaft ab.
     * 
     */
    public boolean isIsPerson() {
        return isPerson;
    }

    /**
     * Legt den Wert der isPerson-Eigenschaft fest.
     * 
     */
    public void setIsPerson(boolean value) {
        this.isPerson = value;
    }

    /**
     * Ruft den Wert der isPlaceholder-Eigenschaft ab.
     * 
     */
    public boolean isIsPlaceholder() {
        return isPlaceholder;
    }

    /**
     * Legt den Wert der isPlaceholder-Eigenschaft fest.
     * 
     */
    public void setIsPlaceholder(boolean value) {
        this.isPlaceholder = value;
    }

    /**
     * Ruft den Wert der person-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Lecturer }
     *     
     */
    public Lecturer getPerson() {
        return person;
    }

    /**
     * Legt den Wert der person-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Lecturer }
     *     
     */
    public void setPerson(Lecturer value) {
        this.person = value;
    }

    /**
     * Ruft den Wert der placeholder-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Lecturer }
     *     
     */
    public Lecturer getPlaceholder() {
        return placeholder;
    }

    /**
     * Legt den Wert der placeholder-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Lecturer }
     *     
     */
    public void setPlaceholder(Lecturer value) {
        this.placeholder = value;
    }

    /**
     * Ruft den Wert der position-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPosition() {
        return position;
    }

    /**
     * Legt den Wert der position-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPosition(BigInteger value) {
        this.position = value;
    }

}
