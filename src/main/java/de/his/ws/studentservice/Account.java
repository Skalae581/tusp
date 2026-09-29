
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für Account complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Account"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="passwordhash" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="validFrom" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="validTo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="accountauthId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="blockedId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="authinfo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="externalsystemId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="isInitialpassword" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isLdapAccount" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="optincookie" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="passwordValidTo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="sourcefilter" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="purposeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", propOrder = {
    "id",
    "personId",
    "username",
    "passwordhash",
    "validFrom",
    "validTo",
    "accountauthId",
    "blockedId",
    "authinfo",
    "externalsystemId",
    "isInitialpassword",
    "isLdapAccount",
    "optincookie",
    "passwordValidTo",
    "sourcefilter",
    "purposeId"
})
public class Account {

    protected long id;
    protected long personId;
    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String passwordhash;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validFrom;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar validTo;
    protected long accountauthId;
    protected long blockedId;
    @XmlElement(required = true, nillable = true)
    protected String authinfo;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long externalsystemId;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean isInitialpassword;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean isLdapAccount;
    @XmlElement(required = true, nillable = true)
    protected String optincookie;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar passwordValidTo;
    @XmlElement(required = true, nillable = true)
    protected String sourcefilter;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long purposeId;

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
     * Ruft den Wert der personId-Eigenschaft ab.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Legt den Wert der personId-Eigenschaft fest.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
    }

    /**
     * Ruft den Wert der username-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Legt den Wert der username-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Ruft den Wert der passwordhash-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswordhash() {
        return passwordhash;
    }

    /**
     * Legt den Wert der passwordhash-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswordhash(String value) {
        this.passwordhash = value;
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
     * Ruft den Wert der accountauthId-Eigenschaft ab.
     * 
     */
    public long getAccountauthId() {
        return accountauthId;
    }

    /**
     * Legt den Wert der accountauthId-Eigenschaft fest.
     * 
     */
    public void setAccountauthId(long value) {
        this.accountauthId = value;
    }

    /**
     * Ruft den Wert der blockedId-Eigenschaft ab.
     * 
     */
    public long getBlockedId() {
        return blockedId;
    }

    /**
     * Legt den Wert der blockedId-Eigenschaft fest.
     * 
     */
    public void setBlockedId(long value) {
        this.blockedId = value;
    }

    /**
     * Ruft den Wert der authinfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthinfo() {
        return authinfo;
    }

    /**
     * Legt den Wert der authinfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthinfo(String value) {
        this.authinfo = value;
    }

    /**
     * Ruft den Wert der externalsystemId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExternalsystemId() {
        return externalsystemId;
    }

    /**
     * Legt den Wert der externalsystemId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExternalsystemId(Long value) {
        this.externalsystemId = value;
    }

    /**
     * Ruft den Wert der isInitialpassword-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsInitialpassword() {
        return isInitialpassword;
    }

    /**
     * Legt den Wert der isInitialpassword-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInitialpassword(Boolean value) {
        this.isInitialpassword = value;
    }

    /**
     * Ruft den Wert der isLdapAccount-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsLdapAccount() {
        return isLdapAccount;
    }

    /**
     * Legt den Wert der isLdapAccount-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsLdapAccount(Boolean value) {
        this.isLdapAccount = value;
    }

    /**
     * Ruft den Wert der optincookie-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptincookie() {
        return optincookie;
    }

    /**
     * Legt den Wert der optincookie-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptincookie(String value) {
        this.optincookie = value;
    }

    /**
     * Ruft den Wert der passwordValidTo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPasswordValidTo() {
        return passwordValidTo;
    }

    /**
     * Legt den Wert der passwordValidTo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPasswordValidTo(XMLGregorianCalendar value) {
        this.passwordValidTo = value;
    }

    /**
     * Ruft den Wert der sourcefilter-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcefilter() {
        return sourcefilter;
    }

    /**
     * Legt den Wert der sourcefilter-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcefilter(String value) {
        this.sourcefilter = value;
    }

    /**
     * Ruft den Wert der purposeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPurposeId() {
        return purposeId;
    }

    /**
     * Legt den Wert der purposeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPurposeId(Long value) {
        this.purposeId = value;
    }

}
