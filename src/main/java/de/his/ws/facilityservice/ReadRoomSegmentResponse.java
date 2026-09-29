
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
 *         &lt;element name="roomSegment" type="{http://www.his.de/ws/FacilityService}RoomSegment"/&gt;
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
@XmlRootElement(name = "readRoomSegmentResponse")
public class ReadRoomSegmentResponse {

    @XmlElement(required = true)
    protected RoomSegment roomSegment;

    /**
     * Ruft den Wert der roomSegment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomSegment }
     *     
     */
    public RoomSegment getRoomSegment() {
        return roomSegment;
    }

    /**
     * Legt den Wert der roomSegment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomSegment }
     *     
     */
    public void setRoomSegment(RoomSegment value) {
        this.roomSegment = value;
    }

}
