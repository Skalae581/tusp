
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
 *         &lt;element name="orgrole" type="{http://www.his.de/ws/RightsAndRolesService}Orgrole"/&gt;
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
    "orgrole"
})
@XmlRootElement(name = "updateOrgrole")
public class UpdateOrgrole {

    @XmlElement(required = true)
    protected Orgrole orgrole;

    /**
     * Ruft den Wert der orgrole-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Orgrole }
     *     
     */
    public Orgrole getOrgrole() {
        return orgrole;
    }

    /**
     * Legt den Wert der orgrole-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Orgrole }
     *     
     */
    public void setOrgrole(Orgrole value) {
        this.orgrole = value;
    }

}
