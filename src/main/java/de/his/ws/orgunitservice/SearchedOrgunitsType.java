
package de.his.ws.orgunitservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für searchedOrgunitsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="searchedOrgunitsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="searchedOrgunit" type="{http://www.his.de/ws/OrgUnitService}SearchedOrgunit60" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchedOrgunitsType", propOrder = {
    "searchedOrgunit"
})
public class SearchedOrgunitsType {

    protected List<SearchedOrgunit60> searchedOrgunit;

    /**
     * Gets the value of the searchedOrgunit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchedOrgunit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchedOrgunit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchedOrgunit60 }
     * 
     * 
     */
    public List<SearchedOrgunit60> getSearchedOrgunit() {
        if (searchedOrgunit == null) {
            searchedOrgunit = new ArrayList<SearchedOrgunit60>();
        }
        return this.searchedOrgunit;
    }

}
