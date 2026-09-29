
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
 *         &lt;element name="children61" type="{http://www.his.de/ws/OrgUnitService}children61Type"/&gt;
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
    "children61"
})
@XmlRootElement(name = "readOrgUnitsChildren61Response")
public class ReadOrgUnitsChildren61Response {

    @XmlElement(required = true)
    protected Children61Type children61;

    /**
     * Ruft den Wert der children61-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Children61Type }
     *     
     */
    public Children61Type getChildren61() {
        return children61;
    }

    /**
     * Legt den Wert der children61-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Children61Type }
     *     
     */
    public void setChildren61(Children61Type value) {
        this.children61 = value;
    }

}
