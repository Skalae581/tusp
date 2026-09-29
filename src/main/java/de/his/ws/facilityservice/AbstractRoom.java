
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AbstractRoom complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AbstractRoom"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/FacilityService}AbstractBuilding"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="partOfRoomComposition" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="classRoomName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractRoom", propOrder = {
    "description",
    "partOfRoomComposition",
    "classRoomName"
})
@XmlSeeAlso({
    RoomSegment.class,
    RoomComposition.class,
    Room.class
})
public abstract class AbstractRoom
    extends AbstractBuilding
{

    @XmlElement(required = true, nillable = true)
    protected String description;
    protected boolean partOfRoomComposition;
    @XmlElement(required = true, nillable = true)
    protected String classRoomName;

    /**
     * Ruft den Wert der description-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Legt den Wert der description-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Ruft den Wert der partOfRoomComposition-Eigenschaft ab.
     * 
     */
    public boolean isPartOfRoomComposition() {
        return partOfRoomComposition;
    }

    /**
     * Legt den Wert der partOfRoomComposition-Eigenschaft fest.
     * 
     */
    public void setPartOfRoomComposition(boolean value) {
        this.partOfRoomComposition = value;
    }

    /**
     * Ruft den Wert der classRoomName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassRoomName() {
        return classRoomName;
    }

    /**
     * Legt den Wert der classRoomName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassRoomName(String value) {
        this.classRoomName = value;
    }

}
