
package de.his.ws.facilityservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Floor complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Floor"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/FacilityService}AbstractBuilding"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="buildingId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="roomCount" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="campusDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Floor", propOrder = {
    "buildingId",
    "roomCount",
    "campusDefaulttext"
})
@XmlSeeAlso({
    Floor202106 .class
})
public class Floor
    extends AbstractBuilding
{

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long buildingId;
    @XmlElement(required = true, nillable = true)
    protected BigInteger roomCount;
    @XmlElement(required = true, nillable = true)
    protected String campusDefaulttext;

    /**
     * Ruft den Wert der buildingId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBuildingId() {
        return buildingId;
    }

    /**
     * Legt den Wert der buildingId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBuildingId(Long value) {
        this.buildingId = value;
    }

    /**
     * Ruft den Wert der roomCount-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRoomCount() {
        return roomCount;
    }

    /**
     * Legt den Wert der roomCount-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRoomCount(BigInteger value) {
        this.roomCount = value;
    }

    /**
     * Ruft den Wert der campusDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampusDefaulttext() {
        return campusDefaulttext;
    }

    /**
     * Legt den Wert der campusDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampusDefaulttext(String value) {
        this.campusDefaulttext = value;
    }

}
