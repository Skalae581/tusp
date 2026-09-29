
package de.his.ws.applicantservice;

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
 *         &lt;element name="applicationContents" type="{http://www.his.de/ws/ApplicantService}applicationContentsType"/&gt;
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
    "applicationContents"
})
@XmlRootElement(name = "readApplicationContentResponse")
public class ReadApplicationContentResponse {

    @XmlElement(required = true)
    protected ApplicationContentsType applicationContents;

    /**
     * Ruft den Wert der applicationContents-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationContentsType }
     *     
     */
    public ApplicationContentsType getApplicationContents() {
        return applicationContents;
    }

    /**
     * Legt den Wert der applicationContents-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationContentsType }
     *     
     */
    public void setApplicationContents(ApplicationContentsType value) {
        this.applicationContents = value;
    }

}
