
package de.his.ws.studentservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Examimport complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Examimport"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="foreignGrade" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="district" type="{http://www.his.de/ws/StudentService}District"/&gt;
 *         &lt;element name="country" type="{http://www.his.de/ws/StudentService}Country"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Examimport", propOrder = {
    "foreignGrade",
    "district",
    "country"
})
public class Examimport {

    @XmlElement(required = true, nillable = true)
    protected BigDecimal foreignGrade;
    @XmlElement(required = true, nillable = true)
    protected District district;
    @XmlElement(required = true, nillable = true)
    protected Country country;

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
     * Ruft den Wert der district-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link District }
     *     
     */
    public District getDistrict() {
        return district;
    }

    /**
     * Legt den Wert der district-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link District }
     *     
     */
    public void setDistrict(District value) {
        this.district = value;
    }

    /**
     * Ruft den Wert der country-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Country }
     *     
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Legt den Wert der country-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setCountry(Country value) {
        this.country = value;
    }

}
