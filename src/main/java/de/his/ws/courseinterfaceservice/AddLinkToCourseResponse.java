
package de.his.ws.courseinterfaceservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="courseLinkId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "courseLinkId"
})
@XmlRootElement(name = "addLinkToCourseResponse")
public class AddLinkToCourseResponse {

    protected long courseLinkId;

    /**
     * Ruft den Wert der courseLinkId-Eigenschaft ab.
     * 
     */
    public long getCourseLinkId() {
        return courseLinkId;
    }

    /**
     * Legt den Wert der courseLinkId-Eigenschaft fest.
     * 
     */
    public void setCourseLinkId(long value) {
        this.courseLinkId = value;
    }

}
