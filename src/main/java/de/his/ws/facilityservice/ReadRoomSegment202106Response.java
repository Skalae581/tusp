
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
 *         &lt;element name="roomSegment" type="{http://www.his.de/ws/FacilityService}RoomSegment202106"/&gt;
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
    "roomSegment"
})
@XmlRootElement(name = "readRoomSegment202106Response")
public class ReadRoomSegment202106Response {

    @XmlElement(required = true)
    protected RoomSegment202106 roomSegment;

    /**
     * Ruft den Wert der roomSegment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomSegment202106 }
     *     
     */
    public RoomSegment202106 getRoomSegment() {
        return roomSegment;
    }

    /**
     * Legt den Wert der roomSegment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomSegment202106 }
     *     
     */
    public void setRoomSegment(RoomSegment202106 value) {
        this.roomSegment = value;
    }

}
