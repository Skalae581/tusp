
package de.his.ws.studentservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für DegreeProgramDto70 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DegreeProgramDto70"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="extensiondate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="extensionsemester" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="genderId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="progress70" type="{http://www.his.de/ws/StudentService}progress70Type"/&gt;
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DegreeProgramDto70", propOrder = {
    "extensiondate",
    "extensionsemester",
    "genderId",
    "progress70",
    "studentId"
})
public class DegreeProgramDto70 {

    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar extensiondate;
    @XmlElement(nillable = true)
    protected BigDecimal extensionsemester;
    @XmlElement(nillable = true)
    protected Long genderId;
    @XmlElement(required = true, nillable = true)
    protected Progress70Type progress70;
    @XmlElement(nillable = true)
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
     * Ruft den Wert der progress70-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Progress70Type }
     *     
     */
    public Progress70Type getProgress70() {
        return progress70;
    }

    /**
     * Legt den Wert der progress70-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Progress70Type }
     *     
     */
    public void setProgress70(Progress70Type value) {
        this.progress70 = value;
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
