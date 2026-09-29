
package de.his.ws.courseinterfaceservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für courseLinksType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="courseLinksType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="courseLink" type="{http://www.his.de/ws/CourseInterfaceService}UnitTermLinkDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "courseLinksType", propOrder = {
    "courseLink"
})
public class CourseLinksType {

    protected List<UnitTermLinkDto> courseLink;

    /**
     * Gets the value of the courseLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the courseLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCourseLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnitTermLinkDto }
     * 
     * 
     */
    public List<UnitTermLinkDto> getCourseLink() {
        if (courseLink == null) {
            courseLink = new ArrayList<UnitTermLinkDto>();
        }
        return this.courseLink;
    }

}
