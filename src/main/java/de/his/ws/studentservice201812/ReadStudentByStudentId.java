
package de.his.ws.studentservice201812;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="withDegreePrograms" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="withAddresses" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="withAccounts" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="withPersonAttributes" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "studentId",
    "withDegreePrograms",
    "withAddresses",
    "withAccounts",
    "withPersonAttributes"
})
@XmlRootElement(name = "readStudentByStudentId")
public class ReadStudentByStudentId {

    protected long studentId;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean withDegreePrograms;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean withAddresses;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean withAccounts;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean withPersonAttributes;

    /**
     * Ruft den Wert der studentId-Eigenschaft ab.
     * 
     */
    public long getStudentId() {
        return studentId;
    }

    /**
     * Legt den Wert der studentId-Eigenschaft fest.
     * 
     */
    public void setStudentId(long value) {
        this.studentId = value;
    }

    /**
     * Ruft den Wert der withDegreePrograms-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWithDegreePrograms() {
        return withDegreePrograms;
    }

    /**
     * Legt den Wert der withDegreePrograms-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWithDegreePrograms(Boolean value) {
        this.withDegreePrograms = value;
    }

    /**
     * Ruft den Wert der withAddresses-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWithAddresses() {
        return withAddresses;
    }

    /**
     * Legt den Wert der withAddresses-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWithAddresses(Boolean value) {
        this.withAddresses = value;
    }

    /**
     * Ruft den Wert der withAccounts-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWithAccounts() {
        return withAccounts;
    }

    /**
     * Legt den Wert der withAccounts-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWithAccounts(Boolean value) {
        this.withAccounts = value;
    }

    /**
     * Ruft den Wert der withPersonAttributes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWithPersonAttributes() {
        return withPersonAttributes;
    }

    /**
     * Legt den Wert der withPersonAttributes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWithPersonAttributes(Boolean value) {
        this.withPersonAttributes = value;
    }

}
