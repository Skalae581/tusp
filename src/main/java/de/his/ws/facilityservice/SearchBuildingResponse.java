
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
 *         &lt;element name="searchedBuildings" type="{http://www.his.de/ws/FacilityService}searchedBuildingsType"/&gt;
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
    "searchedBuildings"
})
@XmlRootElement(name = "searchBuildingResponse")
public class SearchBuildingResponse {

    @XmlElement(required = true)
    protected SearchedBuildingsType searchedBuildings;

    /**
     * Ruft den Wert der searchedBuildings-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SearchedBuildingsType }
     *     
     */
    public SearchedBuildingsType getSearchedBuildings() {
        return searchedBuildings;
    }

    /**
     * Legt den Wert der searchedBuildings-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchedBuildingsType }
     *     
     */
    public void setSearchedBuildings(SearchedBuildingsType value) {
        this.searchedBuildings = value;
    }

}
