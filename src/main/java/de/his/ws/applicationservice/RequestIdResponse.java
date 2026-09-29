
package de.his.ws.applicationservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RequestIdResponse complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RequestIdResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="requestSubjectIdSet" type="{http://www.his.de/ws/ApplicationService}requestSubjectIdSetType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestIdResponse", propOrder = {
    "requestId",
    "requestSubjectIdSet"
})
public class RequestIdResponse {

    protected long requestId;
    @XmlElement(required = true)
    protected RequestSubjectIdSetType requestSubjectIdSet;

    /**
     * Ruft den Wert der requestId-Eigenschaft ab.
     * 
     */
    public long getRequestId() {
        return requestId;
    }

    /**
     * Legt den Wert der requestId-Eigenschaft fest.
     * 
     */
    public void setRequestId(long value) {
        this.requestId = value;
    }

    /**
     * Ruft den Wert der requestSubjectIdSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RequestSubjectIdSetType }
     *     
     */
    public RequestSubjectIdSetType getRequestSubjectIdSet() {
        return requestSubjectIdSet;
    }

    /**
     * Legt den Wert der requestSubjectIdSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestSubjectIdSetType }
     *     
     */
    public void setRequestSubjectIdSet(RequestSubjectIdSetType value) {
        this.requestSubjectIdSet = value;
    }

}
