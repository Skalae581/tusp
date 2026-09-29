
package de.his.ws.personservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für PersonAttributeTypeDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PersonAttributeTypeDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="dataType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fieldLength" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="fieldName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="keyTableType" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="personAttributeTypeContextList" type="{http://www.his.de/ws/PersonService}personAttributeTypeContextListType"/&gt;
 *         &lt;element name="personAttributeValueList" type="{http://www.his.de/ws/PersonService}personAttributeValueListType"/&gt;
 *         &lt;element name="purposes" type="{http://www.his.de/ws/PersonService}purposesType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonAttributeTypeDto", propOrder = {
    "id",
    "dataType",
    "fieldLength",
    "fieldName",
    "keyTableType",
    "personAttributeTypeContextList",
    "personAttributeValueList",
    "purposes"
})
public class PersonAttributeTypeDto {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElement(required = true, nillable = true)
    protected String dataType;
    @XmlElement(required = true, nillable = true)
    protected BigInteger fieldLength;
    @XmlElement(required = true)
    protected String fieldName;
    protected boolean keyTableType;
    @XmlElement(required = true, nillable = true)
    protected PersonAttributeTypeContextListType personAttributeTypeContextList;
    @XmlElement(required = true, nillable = true)
    protected PersonAttributeValueListType personAttributeValueList;
    @XmlElement(required = true, nillable = true)
    protected PurposesType purposes;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der dataType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Legt den Wert der dataType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    /**
     * Ruft den Wert der fieldLength-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFieldLength() {
        return fieldLength;
    }

    /**
     * Legt den Wert der fieldLength-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFieldLength(BigInteger value) {
        this.fieldLength = value;
    }

    /**
     * Ruft den Wert der fieldName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Legt den Wert der fieldName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Ruft den Wert der keyTableType-Eigenschaft ab.
     * 
     */
    public boolean isKeyTableType() {
        return keyTableType;
    }

    /**
     * Legt den Wert der keyTableType-Eigenschaft fest.
     * 
     */
    public void setKeyTableType(boolean value) {
        this.keyTableType = value;
    }

    /**
     * Ruft den Wert der personAttributeTypeContextList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonAttributeTypeContextListType }
     *     
     */
    public PersonAttributeTypeContextListType getPersonAttributeTypeContextList() {
        return personAttributeTypeContextList;
    }

    /**
     * Legt den Wert der personAttributeTypeContextList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonAttributeTypeContextListType }
     *     
     */
    public void setPersonAttributeTypeContextList(PersonAttributeTypeContextListType value) {
        this.personAttributeTypeContextList = value;
    }

    /**
     * Ruft den Wert der personAttributeValueList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonAttributeValueListType }
     *     
     */
    public PersonAttributeValueListType getPersonAttributeValueList() {
        return personAttributeValueList;
    }

    /**
     * Legt den Wert der personAttributeValueList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonAttributeValueListType }
     *     
     */
    public void setPersonAttributeValueList(PersonAttributeValueListType value) {
        this.personAttributeValueList = value;
    }

    /**
     * Ruft den Wert der purposes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PurposesType }
     *     
     */
    public PurposesType getPurposes() {
        return purposes;
    }

    /**
     * Legt den Wert der purposes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PurposesType }
     *     
     */
    public void setPurposes(PurposesType value) {
        this.purposes = value;
    }

}
