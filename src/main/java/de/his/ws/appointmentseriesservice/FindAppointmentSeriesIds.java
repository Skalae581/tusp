
package de.his.ws.appointmentseriesservice;

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
 *         &lt;element name="unitId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="termYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="termTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="planelementId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="individualDatesId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="planelementCancellationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="planelementChangeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="planelementEditingStatusId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "unitId",
    "termYear",
    "termTypeId",
    "planelementId",
    "individualDatesId",
    "planelementCancellationId",
    "planelementChangeId",
    "planelementEditingStatusId"
})
@XmlRootElement(name = "findAppointmentSeriesIds")
public class FindAppointmentSeriesIds {

    @XmlElement(nillable = true)
    protected String unitId;
    @XmlElement(nillable = true)
    protected String termYear;
    @XmlElement(nillable = true)
    protected String termTypeId;
    @XmlElement(nillable = true)
    protected String planelementId;
    @XmlElement(nillable = true)
    protected String individualDatesId;
    @XmlElement(nillable = true)
    protected String planelementCancellationId;
    @XmlElement(nillable = true)
    protected String planelementChangeId;
    @XmlElement(nillable = true)
    protected String planelementEditingStatusId;

    /**
     * Ruft den Wert der unitId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Legt den Wert der unitId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitId(String value) {
        this.unitId = value;
    }

    /**
     * Ruft den Wert der termYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermYear() {
        return termYear;
    }

    /**
     * Legt den Wert der termYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermYear(String value) {
        this.termYear = value;
    }

    /**
     * Ruft den Wert der termTypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermTypeId() {
        return termTypeId;
    }

    /**
     * Legt den Wert der termTypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermTypeId(String value) {
        this.termTypeId = value;
    }

    /**
     * Ruft den Wert der planelementId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanelementId() {
        return planelementId;
    }

    /**
     * Legt den Wert der planelementId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanelementId(String value) {
        this.planelementId = value;
    }

    /**
     * Ruft den Wert der individualDatesId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualDatesId() {
        return individualDatesId;
    }

    /**
     * Legt den Wert der individualDatesId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualDatesId(String value) {
        this.individualDatesId = value;
    }

    /**
     * Ruft den Wert der planelementCancellationId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanelementCancellationId() {
        return planelementCancellationId;
    }

    /**
     * Legt den Wert der planelementCancellationId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanelementCancellationId(String value) {
        this.planelementCancellationId = value;
    }

    /**
     * Ruft den Wert der planelementChangeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanelementChangeId() {
        return planelementChangeId;
    }

    /**
     * Legt den Wert der planelementChangeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanelementChangeId(String value) {
        this.planelementChangeId = value;
    }

    /**
     * Ruft den Wert der planelementEditingStatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanelementEditingStatusId() {
        return planelementEditingStatusId;
    }

    /**
     * Legt den Wert der planelementEditingStatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanelementEditingStatusId(String value) {
        this.planelementEditingStatusId = value;
    }

}
