
package de.his.ws.personaddressservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für MessengerMeta complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="MessengerMeta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/PersonAddressService}AddressMeta"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="messenger" type="{http://www.his.de/ws/PersonAddressService}Messenger"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessengerMeta", propOrder = {
    "messenger"
})
public class MessengerMeta
    extends AddressMeta
{

    @XmlElement(required = true)
    protected Messenger messenger;

    /**
     * Ruft den Wert der messenger-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Messenger }
     *     
     */
    public Messenger getMessenger() {
        return messenger;
    }

    /**
     * Legt den Wert der messenger-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Messenger }
     *     
     */
    public void setMessenger(Messenger value) {
        this.messenger = value;
    }

}
