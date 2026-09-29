
package de.his.ws.rightsandrolesservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RightsInRole complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RightsInRole"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/RightsAndRolesService}DtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="rightsDefinitionId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="rightsParameters" type="{http://www.his.de/ws/RightsAndRolesService}rightsParametersType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RightsInRole", propOrder = {
    "roleId",
    "rightsDefinitionId",
    "rightsParameters"
})
public class RightsInRole
    extends DtoBase
{

    protected long roleId;
    protected long rightsDefinitionId;
    @XmlElement(required = true, nillable = true)
    protected RightsParametersType rightsParameters;

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
     * Ruft den Wert der rightsDefinitionId-Eigenschaft ab.
     * 
     */
    public long getRightsDefinitionId() {
        return rightsDefinitionId;
    }

    /**
     * Legt den Wert der rightsDefinitionId-Eigenschaft fest.
     * 
     */
    public void setRightsDefinitionId(long value) {
        this.rightsDefinitionId = value;
    }

    /**
     * Ruft den Wert der rightsParameters-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RightsParametersType }
     *     
     */
    public RightsParametersType getRightsParameters() {
        return rightsParameters;
    }

    /**
     * Legt den Wert der rightsParameters-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RightsParametersType }
     *     
     */
    public void setRightsParameters(RightsParametersType value) {
        this.rightsParameters = value;
    }

}
