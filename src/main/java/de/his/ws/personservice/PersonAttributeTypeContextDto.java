
package de.his.ws.personservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Specifies a set of roles. The owner of the attribute is required to possess at least one of those roles when the attribute
 *                     is assigned.
 * 
 * <p>Java-Klasse für PersonAttributeTypeContextDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PersonAttributeTypeContextDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="personAttributeTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
@XmlType(name = "PersonAttributeTypeContextDto", propOrder = {
    "id",
    "personAttributeTypeId",
    "roleId"
})
public class PersonAttributeTypeContextDto {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    protected long personAttributeTypeId;
    protected long roleId;

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
     * Ruft den Wert der personAttributeTypeId-Eigenschaft ab.
     * 
     */
    public long getPersonAttributeTypeId() {
        return personAttributeTypeId;
    }

    /**
     * Legt den Wert der personAttributeTypeId-Eigenschaft fest.
     * 
     */
    public void setPersonAttributeTypeId(long value) {
        this.personAttributeTypeId = value;
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

}
