
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RoomComposition complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RoomComposition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/FacilityService}AbstractRoom"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="roomComponents" type="{http://www.his.de/ws/FacilityService}roomComponentsType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomComposition", propOrder = {
    "roomComponents"
})
@XmlSeeAlso({
    RoomComposition202106 .class
})
public class RoomComposition
    extends AbstractRoom
{

    @XmlElement(required = true, nillable = true)
    protected RoomComponentsType roomComponents;

    /**
     * Ruft den Wert der roomComponents-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomComponentsType }
     *     
     */
    public RoomComponentsType getRoomComponents() {
        return roomComponents;
    }

    /**
     * Legt den Wert der roomComponents-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomComponentsType }
     *     
     */
    public void setRoomComponents(RoomComponentsType value) {
        this.roomComponents = value;
    }

}
