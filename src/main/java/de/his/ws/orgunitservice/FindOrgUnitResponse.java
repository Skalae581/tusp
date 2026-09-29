
package de.his.ws.orgunitservice;

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
 *         &lt;element name="orgUnitIds" type="{http://www.his.de/ws/OrgUnitService}orgUnitIdsType"/&gt;
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
    "orgUnitIds"
})
@XmlRootElement(name = "findOrgUnitResponse")
public class FindOrgUnitResponse {

    @XmlElement(required = true)
    protected OrgUnitIdsType orgUnitIds;

    /**
     * Ruft den Wert der orgUnitIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OrgUnitIdsType }
     *     
     */
    public OrgUnitIdsType getOrgUnitIds() {
        return orgUnitIds;
    }

    /**
     * Legt den Wert der orgUnitIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgUnitIdsType }
     *     
     */
    public void setOrgUnitIds(OrgUnitIdsType value) {
        this.orgUnitIds = value;
    }

}
