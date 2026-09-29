
package de.his.ws.studentservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für DegreeProgramDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DegreeProgramDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="extensiondate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="extensionsemester" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="genderId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="progress" type="{http://www.his.de/ws/StudentService}progressType"/&gt;
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DegreeProgramDto", propOrder = {
    "extensiondate",
    "extensionsemester",
    "genderId",
    "progress",
    "studentId"
})
public class DegreeProgramDto {

    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar extensiondate;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal extensionsemester;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long genderId;
    @XmlElement(required = true, nillable = true)
    protected ProgressType progress;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long studentId;

    /**
     * Ruft den Wert der extensiondate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExtensiondate() {
        return extensiondate;
    }

    /**
     * Legt den Wert der extensiondate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExtensiondate(XMLGregorianCalendar value) {
        this.extensiondate = value;
    }

    /**
     * Ruft den Wert der extensionsemester-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExtensionsemester() {
        return extensionsemester;
    }

    /**
     * Legt den Wert der extensionsemester-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExtensionsemester(BigDecimal value) {
        this.extensionsemester = value;
    }

    /**
     * Ruft den Wert der genderId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGenderId() {
        return genderId;
    }

    /**
     * Legt den Wert der genderId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGenderId(Long value) {
        this.genderId = value;
    }

    /**
     * Ruft den Wert der progress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ProgressType }
     *     
     */
    public ProgressType getProgress() {
        return progress;
    }

    /**
     * Legt den Wert der progress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ProgressType }
     *     
     */
    public void setProgress(ProgressType value) {
        this.progress = value;
    }

    /**
     * Ruft den Wert der studentId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * Legt den Wert der studentId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStudentId(Long value) {
        this.studentId = value;
    }

}
