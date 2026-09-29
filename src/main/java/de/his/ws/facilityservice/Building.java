
package de.his.ws.facilityservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Building complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Building"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/FacilityService}AbstractBuilding"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="floorCount" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="campusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="inventorynumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Building", propOrder = {
    "floorCount",
    "campusId",
    "inventorynumber",
    "longitude",
    "latitude"
})
@XmlSeeAlso({
    Building202106 .class
})
public class Building
    extends AbstractBuilding
{

    @XmlElement(required = true)
    protected BigInteger floorCount;
    protected long campusId;
    @XmlElement(required = true, nillable = true)
    protected String inventorynumber;
    @XmlElement(required = true, nillable = true)
    protected String longitude;
    @XmlElement(required = true, nillable = true)
    protected String latitude;

    /**
     * Ruft den Wert der floorCount-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFloorCount() {
        return floorCount;
    }

    /**
     * Legt den Wert der floorCount-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFloorCount(BigInteger value) {
        this.floorCount = value;
    }

    /**
     * Ruft den Wert der campusId-Eigenschaft ab.
     * 
     */
    public long getCampusId() {
        return campusId;
    }

    /**
     * Legt den Wert der campusId-Eigenschaft fest.
     * 
     */
    public void setCampusId(long value) {
        this.campusId = value;
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
     * Ruft den Wert der longitude-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Legt den Wert der longitude-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitude(String value) {
        this.longitude = value;
    }

    /**
     * Ruft den Wert der latitude-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Legt den Wert der latitude-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitude(String value) {
        this.latitude = value;
    }

}
