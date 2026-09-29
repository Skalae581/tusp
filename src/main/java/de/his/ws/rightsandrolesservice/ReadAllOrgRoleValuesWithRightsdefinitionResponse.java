
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
 *         &lt;element name="orgRoleValueIds" type="{http://www.his.de/ws/RightsAndRolesService}orgRoleValueIdsType"/&gt;
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
    "orgRoleValueIds"
})
@XmlRootElement(name = "readAllOrgRoleValuesWithRightsdefinitionResponse")
public class ReadAllOrgRoleValuesWithRightsdefinitionResponse {

    @XmlElement(required = true)
    protected OrgRoleValueIdsType orgRoleValueIds;

    /**
     * Ruft den Wert der orgRoleValueIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OrgRoleValueIdsType }
     *     
     */
    public OrgRoleValueIdsType getOrgRoleValueIds() {
        return orgRoleValueIds;
    }

    /**
     * Legt den Wert der orgRoleValueIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgRoleValueIdsType }
     *     
     */
    public void setOrgRoleValueIds(OrgRoleValueIdsType value) {
        this.orgRoleValueIds = value;
    }

}
