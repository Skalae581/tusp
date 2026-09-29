
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
 *         &lt;element name="rightsdefinitionUniqueName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "rightsdefinitionUniqueName"
})
@XmlRootElement(name = "readAllOrgRoleValuesWithRightsdefinition")
public class ReadAllOrgRoleValuesWithRightsdefinition {

    @XmlElement(required = true)
    protected String rightsdefinitionUniqueName;

    /**
     * Ruft den Wert der rightsdefinitionUniqueName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightsdefinitionUniqueName() {
        return rightsdefinitionUniqueName;
    }

    /**
     * Legt den Wert der rightsdefinitionUniqueName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightsdefinitionUniqueName(String value) {
        this.rightsdefinitionUniqueName = value;
    }

}
