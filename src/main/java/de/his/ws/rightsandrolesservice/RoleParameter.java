
package de.his.ws.rightsandrolesservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RoleParameter complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RoleParameter"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/RightsAndRolesService}DtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="parametervalue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoleParameter", propOrder = {
    "parametervalue",
    "roleId"
})
public class RoleParameter
    extends DtoBase
{

    @XmlElement(required = true)
    protected String parametervalue;
    protected long roleId;

    /**
     * Ruft den Wert der parametervalue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParametervalue() {
        return parametervalue;
    }

    /**
     * Legt den Wert der parametervalue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParametervalue(String value) {
        this.parametervalue = value;
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
