
package de.his.ws.courseinterfaceservice;

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
 *         &lt;element name="successfull" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "successfull"
})
@XmlRootElement(name = "deleteLinkFromCourseResponse")
public class DeleteLinkFromCourseResponse {

    protected boolean successfull;

    /**
     * Ruft den Wert der successfull-Eigenschaft ab.
     * 
     */
    public boolean isSuccessfull() {
        return successfull;
    }

    /**
     * Legt den Wert der successfull-Eigenschaft fest.
     * 
     */
    public void setSuccessfull(boolean value) {
        this.successfull = value;
    }

}
