
package de.his.ws.facilityservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RoomLarge201706 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RoomLarge201706"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/FacilityService}Room"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="seatCapacity" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="frequencyofuse" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="inventorynumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="orgunitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="buildingDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="campusDefaulttext" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="buildingId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="plannedDates" type="{http://www.his.de/ws/FacilityService}plannedDatesType"/&gt;
 *         &lt;element name="roomCompositions" type="{http://www.his.de/ws/FacilityService}roomCompositionsType"/&gt;
 *         &lt;element name="roomSegments" type="{http://www.his.de/ws/FacilityService}roomSegmentsType"/&gt;
 *         &lt;element name="equipmentQuantity" type="{http://www.his.de/ws/FacilityService}equipmentQuantityType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomLarge201706", propOrder = {
    "seatCapacity",
    "frequencyofuse",
    "inventorynumber",
    "orgunitId",
    "buildingDefaulttext",
    "campusDefaulttext",
    "buildingId",
    "plannedDates",
    "roomCompositions",
    "roomSegments",
    "equipmentQuantity"
})
@XmlSeeAlso({
    RoomLarge202106 .class
})
public class RoomLarge201706
    extends Room
{

    @XmlElement(required = true, nillable = true)
    protected BigInteger seatCapacity;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long frequencyofuse;
    @XmlElement(required = true, nillable = true)
    protected String inventorynumber;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long orgunitId;
    @XmlElement(required = true, nillable = true)
    protected String buildingDefaulttext;
    @XmlElement(required = true, nillable = true)
    protected String campusDefaulttext;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long buildingId;
    @XmlElement(required = true, nillable = true)
    protected PlannedDatesType plannedDates;
    @XmlElement(required = true, nillable = true)
    protected RoomCompositionsType roomCompositions;
    @XmlElement(required = true, nillable = true)
    protected RoomSegmentsType roomSegments;
    @XmlElement(required = true, nillable = true)
    protected EquipmentQuantityType equipmentQuantity;

    /**
     * Ruft den Wert der seatCapacity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSeatCapacity() {
        return seatCapacity;
    }

    /**
     * Legt den Wert der seatCapacity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSeatCapacity(BigInteger value) {
        this.seatCapacity = value;
    }

    /**
     * Ruft den Wert der frequencyofuse-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFrequencyofuse() {
        return frequencyofuse;
    }

    /**
     * Legt den Wert der frequencyofuse-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFrequencyofuse(Long value) {
        this.frequencyofuse = value;
    }

    /**
     * Ruft den Wert der inventorynumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInventorynumber() {
        return inventorynumber;
    }

    /**
     * Legt den Wert der inventorynumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInventorynumber(String value) {
        this.inventorynumber = value;
    }

    /**
     * Ruft den Wert der orgunitId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrgunitId() {
        return orgunitId;
    }

    /**
     * Legt den Wert der orgunitId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrgunitId(Long value) {
        this.orgunitId = value;
    }

    /**
     * Ruft den Wert der buildingDefaulttext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingDefaulttext() {
        return buildingDefaulttext;
    }

    /**
     * Legt den Wert der buildingDefaulttext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingDefaulttext(String value) {
        this.buildingDefaulttext = value;
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
     * Ruft den Wert der plannedDates-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PlannedDatesType }
     *     
     */
    public PlannedDatesType getPlannedDates() {
        return plannedDates;
    }

    /**
     * Legt den Wert der plannedDates-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PlannedDatesType }
     *     
     */
    public void setPlannedDates(PlannedDatesType value) {
        this.plannedDates = value;
    }

    /**
     * Ruft den Wert der roomCompositions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomCompositionsType }
     *     
     */
    public RoomCompositionsType getRoomCompositions() {
        return roomCompositions;
    }

    /**
     * Legt den Wert der roomCompositions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomCompositionsType }
     *     
     */
    public void setRoomCompositions(RoomCompositionsType value) {
        this.roomCompositions = value;
    }

    /**
     * Ruft den Wert der roomSegments-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoomSegmentsType }
     *     
     */
    public RoomSegmentsType getRoomSegments() {
        return roomSegments;
    }

    /**
     * Legt den Wert der roomSegments-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomSegmentsType }
     *     
     */
    public void setRoomSegments(RoomSegmentsType value) {
        this.roomSegments = value;
    }

    /**
     * Ruft den Wert der equipmentQuantity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentQuantityType }
     *     
     */
    public EquipmentQuantityType getEquipmentQuantity() {
        return equipmentQuantity;
    }

    /**
     * Legt den Wert der equipmentQuantity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentQuantityType }
     *     
     */
    public void setEquipmentQuantity(EquipmentQuantityType value) {
        this.equipmentQuantity = value;
    }

}
