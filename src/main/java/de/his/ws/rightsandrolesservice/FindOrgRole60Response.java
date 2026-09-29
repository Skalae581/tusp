
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
 *         &lt;element name="OrgRoleInfos" type="{http://www.his.de/ws/RightsAndRolesService}OrgRoleInfosType"/&gt;
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
    "orgRoleInfos"
})
@XmlRootElement(name = "findOrgRole60Response")
public class FindOrgRole60Response {

    @XmlElement(name = "OrgRoleInfos", required = true)
    protected OrgRoleInfosType orgRoleInfos;

    /**
     * Ruft den Wert der orgRoleInfos-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OrgRoleInfosType }
     *     
     */
    public OrgRoleInfosType getOrgRoleInfos() {
        return orgRoleInfos;
    }

    /**
     * Legt den Wert der orgRoleInfos-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgRoleInfosType }
     *     
     */
    public void setOrgRoleInfos(OrgRoleInfosType value) {
        this.orgRoleInfos = value;
    }

}
