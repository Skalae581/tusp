
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
 *         &lt;element name="rooms" type="{http://www.his.de/ws/FacilityService}roomsType"/&gt;
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
    "rooms"
})
@XmlRootElement(name = "searchResponse")
public class SearchResponse {

    @XmlElement(required = true)
    protected RoomsType rooms;

    /**
     * Ruft den Wert der rooms-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomsType }
     *     
     */
    public RoomsType getRooms() {
        return rooms;
    }

    /**
     * Legt den Wert der rooms-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomsType }
     *     
     */
    public void setRooms(RoomsType value) {
        this.rooms = value;
    }

}
