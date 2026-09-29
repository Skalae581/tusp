
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
 *         &lt;element name="roomComposition" type="{http://www.his.de/ws/FacilityService}RoomComposition"/&gt;
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
@XmlRootElement(name = "readRoomCompositionResponse")
public class ReadRoomCompositionResponse {

    @XmlElement(required = true)
    protected RoomComposition roomComposition;

    /**
     * Ruft den Wert der roomComposition-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomComposition }
     *     
     */
    public RoomComposition getRoomComposition() {
        return roomComposition;
    }

    /**
     * Legt den Wert der roomComposition-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomComposition }
     *     
     */
    public void setRoomComposition(RoomComposition value) {
        this.roomComposition = value;
    }

}
