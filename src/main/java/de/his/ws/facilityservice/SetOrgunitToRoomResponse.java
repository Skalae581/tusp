
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="isAssigned" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "isAssigned"
})
@XmlRootElement(name = "setOrgunitToRoomResponse")
public class SetOrgunitToRoomResponse {

    protected boolean isAssigned;

    /**
     * Ruft den Wert der isAssigned-Eigenschaft ab.
     * 
     */
    public boolean isIsAssigned() {
        return isAssigned;
    }

    /**
     * Legt den Wert der isAssigned-Eigenschaft fest.
     * 
     */
    public void setIsAssigned(boolean value) {
        this.isAssigned = value;
    }

}
