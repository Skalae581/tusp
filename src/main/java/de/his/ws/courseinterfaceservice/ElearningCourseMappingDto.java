
package de.his.ws.courseinterfaceservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ElearningCourseMapping-object for a course and term
 * 
 * <p>Java-Klasse für ElearningCourseMappingDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ElearningCourseMappingDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="unitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseMappingTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="elearningSystemId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termTypeValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElearningCourseMappingDto", propOrder = {
    "unitId",
    "courseMappingTypeId",
    "elearningSystemId",
    "termTypeValueId",
    "year"
})
public class ElearningCourseMappingDto {

    protected long unitId;
    protected long courseMappingTypeId;
    protected long elearningSystemId;
    protected long termTypeValueId;
    @XmlElement(required = true)
    protected BigInteger year;

    /**
     * Ruft den Wert der unitId-Eigenschaft ab.
     * 
     */
    public long getUnitId() {
        return unitId;
    }

    /**
     * Legt den Wert der unitId-Eigenschaft fest.
     * 
     */
    public void setUnitId(long value) {
        this.unitId = value;
    }

    /**
     * Ruft den Wert der courseMappingTypeId-Eigenschaft ab.
     * 
     */
    public long getCourseMappingTypeId() {
        return courseMappingTypeId;
    }

    /**
     * Legt den Wert der courseMappingTypeId-Eigenschaft fest.
     * 
     */
    public void setCourseMappingTypeId(long value) {
        this.courseMappingTypeId = value;
    }

    /**
     * Ruft den Wert der elearningSystemId-Eigenschaft ab.
     * 
     */
    public long getElearningSystemId() {
        return elearningSystemId;
    }

    /**
     * Legt den Wert der elearningSystemId-Eigenschaft fest.
     * 
     */
    public void setElearningSystemId(long value) {
        this.elearningSystemId = value;
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

}
