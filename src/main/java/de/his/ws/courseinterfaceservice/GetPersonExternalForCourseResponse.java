
package de.his.ws.courseinterfaceservice;

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
 *         &lt;element name="personExternals" type="{http://www.his.de/ws/CourseInterfaceService}personExternalsType"/&gt;
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
    "personExternals"
})
@XmlRootElement(name = "getPersonExternalForCourseResponse")
public class GetPersonExternalForCourseResponse {

    @XmlElement(required = true)
    protected PersonExternalsType personExternals;

    /**
     * Ruft den Wert der personExternals-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonExternalsType }
     *     
     */
    public PersonExternalsType getPersonExternals() {
        return personExternals;
    }

    /**
     * Legt den Wert der personExternals-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonExternalsType }
     *     
     */
    public void setPersonExternals(PersonExternalsType value) {
        this.personExternals = value;
    }

}
