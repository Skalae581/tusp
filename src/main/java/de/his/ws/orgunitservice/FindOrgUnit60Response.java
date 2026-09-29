
package de.his.ws.orgunitservice;

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
 *         &lt;element name="searchedOrgunits" type="{http://www.his.de/ws/OrgUnitService}searchedOrgunitsType"/&gt;
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
    "searchedOrgunits"
})
@XmlRootElement(name = "findOrgUnit60Response")
public class FindOrgUnit60Response {

    @XmlElement(required = true)
    protected SearchedOrgunitsType searchedOrgunits;

    /**
     * Ruft den Wert der searchedOrgunits-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SearchedOrgunitsType }
     *     
     */
    public SearchedOrgunitsType getSearchedOrgunits() {
        return searchedOrgunits;
    }

    /**
     * Legt den Wert der searchedOrgunits-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchedOrgunitsType }
     *     
     */
    public void setSearchedOrgunits(SearchedOrgunitsType value) {
        this.searchedOrgunits = value;
    }

}
