
package de.his.ws.rightsandrolesservice;

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
 *         &lt;element name="orgRoles" type="{http://www.his.de/ws/RightsAndRolesService}orgRolesType"/&gt;
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
    "orgRoles"
})
@XmlRootElement(name = "readRolesForPersonResponse")
public class ReadRolesForPersonResponse {

    @XmlElement(required = true)
    protected OrgRolesType orgRoles;

    /**
     * Ruft den Wert der orgRoles-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OrgRolesType }
     *     
     */
    public OrgRolesType getOrgRoles() {
        return orgRoles;
    }

    /**
     * Legt den Wert der orgRoles-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgRolesType }
     *     
     */
    public void setOrgRoles(OrgRolesType value) {
        this.orgRoles = value;
    }

}
