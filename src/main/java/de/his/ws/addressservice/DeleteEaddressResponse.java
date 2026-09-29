
package de.his.ws.addressservice;

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
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "isDeleted"
})
@XmlRootElement(name = "deleteEaddressResponse")
public class DeleteEaddressResponse {

    protected boolean isDeleted;

    /**
     * Ruft den Wert der isDeleted-Eigenschaft ab.
     * 
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * Legt den Wert der isDeleted-Eigenschaft fest.
     * 
     */
    public void setIsDeleted(boolean value) {
        this.isDeleted = value;
    }

}
