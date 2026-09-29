
package de.his.ws.courseofstudyservice;

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
 *         &lt;element name="searchedCos" type="{http://www.his.de/ws/CourseOfStudyService}searchedCosType"/&gt;
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
    "searchedCos"
})
@XmlRootElement(name = "findCourseOfStudy201812Response")
public class FindCourseOfStudy201812Response {

    @XmlElement(required = true)
    protected SearchedCosType searchedCos;

    /**
     * Ruft den Wert der searchedCos-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SearchedCosType }
     *     
     */
    public SearchedCosType getSearchedCos() {
        return searchedCos;
    }

    /**
     * Legt den Wert der searchedCos-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchedCosType }
     *     
     */
    public void setSearchedCos(SearchedCosType value) {
        this.searchedCos = value;
    }

}
