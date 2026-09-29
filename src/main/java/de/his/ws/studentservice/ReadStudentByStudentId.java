
package de.his.ws.studentservice;

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
 *         &lt;element name="periodId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "periodId",
    "withAddresses",
    "withAccounts",
    "withPersonAttributes"
})
@XmlRootElement(name = "readStudentByStudentId")
public class ReadStudentByStudentId {

    protected long studentId;
    protected boolean withDegreePrograms;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long periodId;
    protected boolean withAddresses;
    protected boolean withAccounts;
    protected boolean withPersonAttributes;

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
     */
    public boolean isWithDegreePrograms() {
        return withDegreePrograms;
    }

    /**
     * Legt den Wert der withDegreePrograms-Eigenschaft fest.
     * 
     */
    public void setWithDegreePrograms(boolean value) {
        this.withDegreePrograms = value;
    }

    /**
     * Ruft den Wert der periodId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPeriodId() {
        return periodId;
    }

    /**
     * Legt den Wert der periodId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPeriodId(Long value) {
        this.periodId = value;
    }

    /**
     * Ruft den Wert der withAddresses-Eigenschaft ab.
     * 
     */
    public boolean isWithAddresses() {
        return withAddresses;
    }

    /**
     * Legt den Wert der withAddresses-Eigenschaft fest.
     * 
     */
    public void setWithAddresses(boolean value) {
        this.withAddresses = value;
    }

    /**
     * Ruft den Wert der withAccounts-Eigenschaft ab.
     * 
     */
    public boolean isWithAccounts() {
        return withAccounts;
    }

    /**
     * Legt den Wert der withAccounts-Eigenschaft fest.
     * 
     */
    public void setWithAccounts(boolean value) {
        this.withAccounts = value;
    }

    /**
     * Ruft den Wert der withPersonAttributes-Eigenschaft ab.
     * 
     */
    public boolean isWithPersonAttributes() {
        return withPersonAttributes;
    }

    /**
     * Legt den Wert der withPersonAttributes-Eigenschaft fest.
     * 
     */
    public void setWithPersonAttributes(boolean value) {
        this.withPersonAttributes = value;
    }

}
