
package de.his.ws.courseofstudyservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für UnitStudiesInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="UnitStudiesInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="unitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseOfStudyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseOfStudyLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="rootElementtypeHiskey" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitStudiesInfo", propOrder = {
    "id",
    "unitId",
    "courseOfStudyId",
    "courseOfStudyLid",
    "rootElementtypeHiskey"
})
public class UnitStudiesInfo {

    protected long id;
    protected long unitId;
    protected long courseOfStudyId;
    protected long courseOfStudyLid;
    @XmlElement(required = true)
    protected BigInteger rootElementtypeHiskey;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

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
     * Ruft den Wert der courseOfStudyId-Eigenschaft ab.
     * 
     */
    public long getCourseOfStudyId() {
        return courseOfStudyId;
    }

    /**
     * Legt den Wert der courseOfStudyId-Eigenschaft fest.
     * 
     */
    public void setCourseOfStudyId(long value) {
        this.courseOfStudyId = value;
    }

    /**
     * Ruft den Wert der courseOfStudyLid-Eigenschaft ab.
     * 
     */
    public long getCourseOfStudyLid() {
        return courseOfStudyLid;
    }

    /**
     * Legt den Wert der courseOfStudyLid-Eigenschaft fest.
     * 
     */
    public void setCourseOfStudyLid(long value) {
        this.courseOfStudyLid = value;
    }

    /**
     * Ruft den Wert der rootElementtypeHiskey-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRootElementtypeHiskey() {
        return rootElementtypeHiskey;
    }

    /**
     * Legt den Wert der rootElementtypeHiskey-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRootElementtypeHiskey(BigInteger value) {
        this.rootElementtypeHiskey = value;
    }

}
