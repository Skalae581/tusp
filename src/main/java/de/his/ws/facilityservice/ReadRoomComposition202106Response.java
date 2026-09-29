
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
 *         &lt;element name="roomComposition" type="{http://www.his.de/ws/FacilityService}RoomComposition202106"/&gt;
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
    "roomComposition"
})
@XmlRootElement(name = "readRoomComposition202106Response")
public class ReadRoomComposition202106Response {

    @XmlElement(required = true)
    protected RoomComposition202106 roomComposition;

    /**
     * Ruft den Wert der roomComposition-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomComposition202106 }
     *     
     */
    public RoomComposition202106 getRoomComposition() {
        return roomComposition;
    }

    /**
     * Legt den Wert der roomComposition-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomComposition202106 }
     *     
     */
    public void setRoomComposition(RoomComposition202106 value) {
        this.roomComposition = value;
    }

}
