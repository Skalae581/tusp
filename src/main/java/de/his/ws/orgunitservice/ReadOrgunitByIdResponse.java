
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
 *         &lt;element name="orgunit" type="{http://www.his.de/ws/OrgUnitService}OrgUnitChildren"/&gt;
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
    "orgunit"
})
@XmlRootElement(name = "readOrgunitByIdResponse")
public class ReadOrgunitByIdResponse {

    @XmlElement(required = true)
    protected OrgUnitChildren orgunit;

    /**
     * Ruft den Wert der orgunit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OrgUnitChildren }
     *     
     */
    public OrgUnitChildren getOrgunit() {
        return orgunit;
    }

    /**
     * Legt den Wert der orgunit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgUnitChildren }
     *     
     */
    public void setOrgunit(OrgUnitChildren value) {
        this.orgunit = value;
    }

}
