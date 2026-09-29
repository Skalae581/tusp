
package de.his.ws.rightsandrolesservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Contains basic information for an orgrole.
 * 
 * <p>Java-Klasse für OrgRoleInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="OrgRoleInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="orgroleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="orgunitLid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="persionId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="validFrom" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="validTo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="roleHiskeyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrgRoleInfo", propOrder = {
    "orgroleId",
    "orgunitLid",
    "roleId",
    "persionId",
    "validFrom",
    "validTo",
    "roleHiskeyId"
})
public class OrgRoleInfo {

    protected long orgroleId;
    protected long orgunitLid;
    protected long roleId;
    protected long persionId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validFrom;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validTo;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long roleHiskeyId;

    /**
     * Ruft den Wert der orgroleId-Eigenschaft ab.
     * 
     */
    public long getOrgroleId() {
        return orgroleId;
    }

    /**
     * Legt den Wert der orgroleId-Eigenschaft fest.
     * 
     */
    public void setOrgroleId(long value) {
        this.orgroleId = value;
    }

    /**
     * Ruft den Wert der orgunitLid-Eigenschaft ab.
     * 
     */
    public long getOrgunitLid() {
        return orgunitLid;
    }

    /**
     * Legt den Wert der orgunitLid-Eigenschaft fest.
     * 
     */
    public void setOrgunitLid(long value) {
        this.orgunitLid = value;
    }

    /**
     * Ruft den Wert der roleId-Eigenschaft ab.
     * 
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * Legt den Wert der roleId-Eigenschaft fest.
     * 
     */
    public void setRoleId(long value) {
        this.roleId = value;
    }

    /**
     * Ruft den Wert der persionId-Eigenschaft ab.
     * 
     */
    public long getPersionId() {
        return persionId;
    }

    /**
     * Legt den Wert der persionId-Eigenschaft fest.
     * 
     */
    public void setPersionId(long value) {
        this.persionId = value;
    }

    /**
     * Ruft den Wert der validFrom-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidFrom() {
        return validFrom;
    }

    /**
     * Legt den Wert der validFrom-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidFrom(XMLGregorianCalendar value) {
        this.validFrom = value;
    }

    /**
     * Ruft den Wert der validTo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidTo() {
        return validTo;
    }

    /**
     * Legt den Wert der validTo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidTo(XMLGregorianCalendar value) {
        this.validTo = value;
    }

    /**
     * Ruft den Wert der roleHiskeyId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRoleHiskeyId() {
        return roleHiskeyId;
    }

    /**
     * Legt den Wert der roleHiskeyId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRoleHiskeyId(Long value) {
        this.roleHiskeyId = value;
    }

}
