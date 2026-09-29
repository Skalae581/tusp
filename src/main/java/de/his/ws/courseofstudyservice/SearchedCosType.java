
package de.his.ws.courseofstudyservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für searchedCosType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="searchedCosType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="searchedCos" type="{http://www.his.de/ws/CourseOfStudyService}SearchedCos" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchedCosType", propOrder = {
    "searchedCos"
})
public class SearchedCosType {

    protected List<SearchedCos> searchedCos;

    /**
     * Gets the value of the searchedCos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchedCos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchedCos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchedCos }
     * 
     * 
     */
    public List<SearchedCos> getSearchedCos() {
        if (searchedCos == null) {
            searchedCos = new ArrayList<SearchedCos>();
        }
        return this.searchedCos;
    }

}
