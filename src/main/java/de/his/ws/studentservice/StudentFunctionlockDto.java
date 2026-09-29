
package de.his.ws.studentservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für StudentFunctionlockDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StudentFunctionlockDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="reasonOfFunctionlockValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studentId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentFunctionlockDto", propOrder = {
    "year",
    "reasonOfFunctionlockValueId",
    "studentId",
    "termTypeValueId"
})
public class StudentFunctionlockDto
    extends GeneratedDtoBase
{

    @XmlElement(required = true)
    protected BigInteger year;
    protected long reasonOfFunctionlockValueId;
    protected long studentId;
    protected long termTypeValueId;

    /**
     * Ruft den Wert der year-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYear() {
        return year;
    }

    /**
     * Legt den Wert der year-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYear(BigInteger value) {
        this.year = value;
    }

    /**
     * Ruft den Wert der reasonOfFunctionlockValueId-Eigenschaft ab.
     * 
     */
    public long getReasonOfFunctionlockValueId() {
        return reasonOfFunctionlockValueId;
    }

    /**
     * Legt den Wert der reasonOfFunctionlockValueId-Eigenschaft fest.
     * 
     */
    public void setReasonOfFunctionlockValueId(long value) {
        this.reasonOfFunctionlockValueId = value;
    }

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
     * Ruft den Wert der termTypeValueId-Eigenschaft ab.
     * 
     */
    public long getTermTypeValueId() {
        return termTypeValueId;
    }

    /**
     * Legt den Wert der termTypeValueId-Eigenschaft fest.
     * 
     */
    public void setTermTypeValueId(long value) {
        this.termTypeValueId = value;
    }

}
