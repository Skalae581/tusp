
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="roomId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="equipmentValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "roomId",
    "equipmentValueId",
    "quantity"
})
@XmlRootElement(name = "setEquipmentToRoom")
public class SetEquipmentToRoom {

    protected long roomId;
    protected long equipmentValueId;
    protected long quantity;

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
     * Ruft den Wert der equipmentValueId-Eigenschaft ab.
     * 
     */
    public long getEquipmentValueId() {
        return equipmentValueId;
    }

    /**
     * Legt den Wert der equipmentValueId-Eigenschaft fest.
     * 
     */
    public void setEquipmentValueId(long value) {
        this.equipmentValueId = value;
    }

    /**
     * Ruft den Wert der quantity-Eigenschaft ab.
     * 
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Legt den Wert der quantity-Eigenschaft fest.
     * 
     */
    public void setQuantity(long value) {
        this.quantity = value;
    }

}
