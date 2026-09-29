
package de.his.ws.applicationservice;

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
 *         &lt;element name="applicationInfoList" type="{http://www.his.de/ws/ApplicationService}applicationInfoListType"/&gt;
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
    "applicationInfoList"
})
@XmlRootElement(name = "findApplicationResponse")
public class FindApplicationResponse {

    @XmlElement(required = true)
    protected ApplicationInfoListType applicationInfoList;

    /**
     * Ruft den Wert der applicationInfoList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationInfoListType }
     *     
     */
    public ApplicationInfoListType getApplicationInfoList() {
        return applicationInfoList;
    }

    /**
     * Legt den Wert der applicationInfoList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationInfoListType }
     *     
     */
    public void setApplicationInfoList(ApplicationInfoListType value) {
        this.applicationInfoList = value;
    }

}
