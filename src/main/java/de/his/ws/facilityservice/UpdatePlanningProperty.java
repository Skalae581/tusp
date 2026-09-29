
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
 *         &lt;element name="centralised" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "centralised"
})
@XmlRootElement(name = "updatePlanningProperty")
public class UpdatePlanningProperty {

    protected long roomId;
    protected boolean centralised;

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
     * Ruft den Wert der centralised-Eigenschaft ab.
     * 
     */
    public boolean isCentralised() {
        return centralised;
    }

    /**
     * Legt den Wert der centralised-Eigenschaft fest.
     * 
     */
    public void setCentralised(boolean value) {
        this.centralised = value;
    }

}
