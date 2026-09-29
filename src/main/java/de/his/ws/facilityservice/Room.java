
package de.his.ws.facilityservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Room complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Room"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/FacilityService}AbstractRoom"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="floorId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="din277RoomuseId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="roomSegmentCount" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Room", propOrder = {
    "floorId",
    "din277RoomuseId",
    "area",
    "roomSegmentCount"
})
@XmlSeeAlso({
    RoomLarge.class,
    Room202006 .class,
    RoomLarge201706 .class,
    Room202106 .class
})
public class Room
    extends AbstractRoom
{

    protected long floorId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long din277RoomuseId;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal area;
    @XmlElement(required = true, nillable = true)
    protected BigInteger roomSegmentCount;

    /**
     * Ruft den Wert der floorId-Eigenschaft ab.
     * 
     */
    public long getFloorId() {
        return floorId;
    }

    /**
     * Legt den Wert der floorId-Eigenschaft fest.
     * 
     */
    public void setFloorId(long value) {
        this.floorId = value;
    }

    /**
     * Ruft den Wert der din277RoomuseId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDin277RoomuseId() {
        return din277RoomuseId;
    }

    /**
     * Legt den Wert der din277RoomuseId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDin277RoomuseId(Long value) {
        this.din277RoomuseId = value;
    }

    /**
     * Ruft den Wert der area-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * Legt den Wert der area-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setArea(BigDecimal value) {
        this.area = value;
    }

    /**
     * Ruft den Wert der roomSegmentCount-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRoomSegmentCount() {
        return roomSegmentCount;
    }

    /**
     * Legt den Wert der roomSegmentCount-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRoomSegmentCount(BigInteger value) {
        this.roomSegmentCount = value;
    }

}
