
package de.his.ws.periodservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für periodUsagesDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="periodUsagesDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="defaultPeriod" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="enrollconfigurationId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="periodId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="periodUsageValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "periodUsagesDto", propOrder = {
    "defaultPeriod",
    "enrollconfigurationId",
    "periodId",
    "periodUsageValueId",
    "roleId"
})
public class PeriodUsagesDto {

    protected boolean defaultPeriod;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long enrollconfigurationId;
    protected long periodId;
    protected long periodUsageValueId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long roleId;

    /**
     * Ruft den Wert der defaultPeriod-Eigenschaft ab.
     * 
     */
    public boolean isDefaultPeriod() {
        return defaultPeriod;
    }

    /**
     * Legt den Wert der defaultPeriod-Eigenschaft fest.
     * 
     */
    public void setDefaultPeriod(boolean value) {
        this.defaultPeriod = value;
    }

    /**
     * Ruft den Wert der enrollconfigurationId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEnrollconfigurationId() {
        return enrollconfigurationId;
    }

    /**
     * Legt den Wert der enrollconfigurationId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEnrollconfigurationId(Long value) {
        this.enrollconfigurationId = value;
    }

    /**
     * Ruft den Wert der periodId-Eigenschaft ab.
     * 
     */
    public long getPeriodId() {
        return periodId;
    }

    /**
     * Legt den Wert der periodId-Eigenschaft fest.
     * 
     */
    public void setPeriodId(long value) {
        this.periodId = value;
    }

    /**
     * Ruft den Wert der periodUsageValueId-Eigenschaft ab.
     * 
     */
    public long getPeriodUsageValueId() {
        return periodUsageValueId;
    }

    /**
     * Legt den Wert der periodUsageValueId-Eigenschaft fest.
     * 
     */
    public void setPeriodUsageValueId(long value) {
        this.periodUsageValueId = value;
    }

    /**
     * Ruft den Wert der roleId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * Legt den Wert der roleId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRoleId(Long value) {
        this.roleId = value;
    }

}
