
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
 *         &lt;element name="buildingId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="openinghoursEntry" type="{http://www.his.de/ws/FacilityService}BuildingOpeninghours"/&gt;
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
    "buildingId",
    "openinghoursEntry"
})
@XmlRootElement(name = "createBuildingOpeninghoursEntry")
public class CreateBuildingOpeninghoursEntry {

    protected long buildingId;
    @XmlElement(required = true)
    protected BuildingOpeninghours openinghoursEntry;

    /**
     * Ruft den Wert der buildingId-Eigenschaft ab.
     * 
     */
    public long getBuildingId() {
        return buildingId;
    }

    /**
     * Legt den Wert der buildingId-Eigenschaft fest.
     * 
     */
    public void setBuildingId(long value) {
        this.buildingId = value;
    }

    /**
     * Ruft den Wert der openinghoursEntry-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BuildingOpeninghours }
     *     
     */
    public BuildingOpeninghours getOpeninghoursEntry() {
        return openinghoursEntry;
    }

    /**
     * Legt den Wert der openinghoursEntry-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BuildingOpeninghours }
     *     
     */
    public void setOpeninghoursEntry(BuildingOpeninghours value) {
        this.openinghoursEntry = value;
    }

}
