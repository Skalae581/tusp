
package de.his.ws.facilityservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für openinghoursType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="openinghoursType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="openinghoursEntry" type="{http://www.his.de/ws/FacilityService}BuildingOpeninghours" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "openinghoursType", propOrder = {
    "openinghoursEntry"
})
public class OpeninghoursType {

    protected List<BuildingOpeninghours> openinghoursEntry;

    /**
     * Gets the value of the openinghoursEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the openinghoursEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpeninghoursEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BuildingOpeninghours }
     * 
     * 
     */
    public List<BuildingOpeninghours> getOpeninghoursEntry() {
        if (openinghoursEntry == null) {
            openinghoursEntry = new ArrayList<BuildingOpeninghours>();
        }
        return this.openinghoursEntry;
    }

}
