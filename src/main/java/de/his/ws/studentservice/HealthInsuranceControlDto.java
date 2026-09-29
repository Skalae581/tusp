
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für HealthInsuranceControlDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="HealthInsuranceControlDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}DtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="healthInsuranceId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="insurantStatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="healthInsuranceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="healthInsuranceText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HealthInsuranceControlDto", propOrder = {
    "healthInsuranceId",
    "insurantStatusId",
    "healthInsuranceNumber",
    "healthInsuranceText"
})
public class HealthInsuranceControlDto
    extends DtoBase
{

    protected long healthInsuranceId;
    protected long insurantStatusId;
    @XmlElement(required = true)
    protected String healthInsuranceNumber;
    @XmlElement(required = true, nillable = true)
    protected String healthInsuranceText;

    /**
     * Ruft den Wert der healthInsuranceId-Eigenschaft ab.
     * 
     */
    public long getHealthInsuranceId() {
        return healthInsuranceId;
    }

    /**
     * Legt den Wert der healthInsuranceId-Eigenschaft fest.
     * 
     */
    public void setHealthInsuranceId(long value) {
        this.healthInsuranceId = value;
    }

    /**
     * Ruft den Wert der insurantStatusId-Eigenschaft ab.
     * 
     */
    public long getInsurantStatusId() {
        return insurantStatusId;
    }

    /**
     * Legt den Wert der insurantStatusId-Eigenschaft fest.
     * 
     */
    public void setInsurantStatusId(long value) {
        this.insurantStatusId = value;
    }

    /**
     * Ruft den Wert der healthInsuranceNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    /**
     * Legt den Wert der healthInsuranceNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthInsuranceNumber(String value) {
        this.healthInsuranceNumber = value;
    }

    /**
     * Ruft den Wert der healthInsuranceText-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthInsuranceText() {
        return healthInsuranceText;
    }

    /**
     * Legt den Wert der healthInsuranceText-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthInsuranceText(String value) {
        this.healthInsuranceText = value;
    }

}
