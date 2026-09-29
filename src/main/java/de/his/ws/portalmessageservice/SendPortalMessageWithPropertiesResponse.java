
package de.his.ws.portalmessageservice;

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
 *         &lt;element name="messageIds" type="{http://www.his.de/ws/PortalMessageService}messageIdsType"/&gt;
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
    "messageIds"
})
@XmlRootElement(name = "sendPortalMessageWithPropertiesResponse")
public class SendPortalMessageWithPropertiesResponse {

    @XmlElement(required = true)
    protected MessageIdsType messageIds;

    /**
     * Ruft den Wert der messageIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MessageIdsType }
     *     
     */
    public MessageIdsType getMessageIds() {
        return messageIds;
    }

    /**
     * Legt den Wert der messageIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageIdsType }
     *     
     */
    public void setMessageIds(MessageIdsType value) {
        this.messageIds = value;
    }

}
