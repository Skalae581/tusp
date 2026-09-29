
package de.his.ws.studentservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Examimport70 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Examimport70"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="foreignGrade" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="countryId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="districtObjId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Examimport70", propOrder = {
    "foreignGrade",
    "countryId",
    "districtId",
    "districtObjId"
})
public class Examimport70
    extends GeneratedDtoBase
{

    @XmlElement(required = true, nillable = true)
    protected BigDecimal foreignGrade;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long countryId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long districtId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long districtObjId;

    /**
     * Ruft den Wert der foreignGrade-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getForeignGrade() {
        return foreignGrade;
    }

    /**
     * Legt den Wert der foreignGrade-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setForeignGrade(BigDecimal value) {
        this.foreignGrade = value;
    }

    /**
     * Ruft den Wert der countryId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * Legt den Wert der countryId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCountryId(Long value) {
        this.countryId = value;
    }

    /**
     * Ruft den Wert der districtId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * Legt den Wert der districtId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDistrictId(Long value) {
        this.districtId = value;
    }

    /**
     * Ruft den Wert der districtObjId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDistrictObjId() {
        return districtObjId;
    }

    /**
     * Legt den Wert der districtObjId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDistrictObjId(Long value) {
        this.districtObjId = value;
    }

}
