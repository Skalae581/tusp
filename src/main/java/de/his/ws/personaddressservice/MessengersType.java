
package de.his.ws.personaddressservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für messengersType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="messengersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="messengerMeta" type="{http://www.his.de/ws/PersonAddressService}MessengerMeta" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "messengersType", propOrder = {
    "messengerMeta"
})
public class MessengersType {

    protected List<MessengerMeta> messengerMeta;

    /**
     * Gets the value of the messengerMeta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messengerMeta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessengerMeta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessengerMeta }
     * 
     * 
     */
    public List<MessengerMeta> getMessengerMeta() {
        if (messengerMeta == null) {
            messengerMeta = new ArrayList<MessengerMeta>();
        }
        return this.messengerMeta;
    }

}
