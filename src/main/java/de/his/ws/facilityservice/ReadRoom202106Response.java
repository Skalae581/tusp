
package de.his.ws.facilityservice;

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
 *         &lt;element name="room" type="{http://www.his.de/ws/FacilityService}RoomLarge202106"/&gt;
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
    "room"
})
@XmlRootElement(name = "readRoom202106Response")
public class ReadRoom202106Response {

    @XmlElement(required = true, nillable = true)
    protected RoomLarge202106 room;

    /**
     * Ruft den Wert der room-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomLarge202106 }
     *     
     */
    public RoomLarge202106 getRoom() {
        return room;
    }

    /**
     * Legt den Wert der room-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomLarge202106 }
     *     
     */
    public void setRoom(RoomLarge202106 value) {
        this.room = value;
    }

}
