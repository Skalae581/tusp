
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für SearchResult complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SearchResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="roomId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="roomTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchResult", propOrder = {
    "roomId",
    "roomTypeId"
})
public class SearchResult {

    protected long roomId;
    protected long roomTypeId;

    /**
     * Ruft den Wert der roomId-Eigenschaft ab.
     * 
     */
    public long getRoomId() {
        return roomId;
    }

    /**
     * Legt den Wert der roomId-Eigenschaft fest.
     * 
     */
    public void setRoomId(long value) {
        this.roomId = value;
    }

    /**
     * Ruft den Wert der roomTypeId-Eigenschaft ab.
     * 
     */
    public long getRoomTypeId() {
        return roomTypeId;
    }

    /**
     * Legt den Wert der roomTypeId-Eigenschaft fest.
     * 
     */
    public void setRoomTypeId(long value) {
        this.roomTypeId = value;
    }

}
