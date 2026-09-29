
package de.his.ws.rightsandrolesservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RightsParameter complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RightsParameter"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/RightsAndRolesService}DtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rightsparameterId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="parametervalue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rightsinroleId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RightsParameter", propOrder = {
    "rightsparameterId",
    "description",
    "parametervalue",
    "rightsinroleId"
})
public class RightsParameter
    extends DtoBase
{

    protected long rightsparameterId;
    @XmlElement(required = true, nillable = true)
    protected String description;
    @XmlElement(required = true)
    protected String parametervalue;
    protected long rightsinroleId;

    /**
     * Ruft den Wert der rightsparameterId-Eigenschaft ab.
     * 
     */
    public long getRightsparameterId() {
        return rightsparameterId;
    }

    /**
     * Legt den Wert der rightsparameterId-Eigenschaft fest.
     * 
     */
    public void setRightsparameterId(long value) {
        this.rightsparameterId = value;
    }

    /**
     * Ruft den Wert der description-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Legt den Wert der description-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

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
     * Ruft den Wert der rightsinroleId-Eigenschaft ab.
     * 
     */
    public long getRightsinroleId() {
        return rightsinroleId;
    }

    /**
     * Legt den Wert der rightsinroleId-Eigenschaft fest.
     * 
     */
    public void setRightsinroleId(long value) {
        this.rightsinroleId = value;
    }

}
