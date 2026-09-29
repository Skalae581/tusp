
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
 *         &lt;element name="personPlanelements" type="{http://www.his.de/ws/CourseInterfaceService}personPlanelementsType"/&gt;
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
    "personPlanelements"
})
@XmlRootElement(name = "getPersonResponsibleForPlanelementResponse")
public class GetPersonResponsibleForPlanelementResponse {

    @XmlElement(required = true)
    protected PersonPlanelementsType personPlanelements;

    /**
     * Ruft den Wert der personPlanelements-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PersonPlanelementsType }
     *     
     */
    public PersonPlanelementsType getPersonPlanelements() {
        return personPlanelements;
    }

    /**
     * Legt den Wert der personPlanelements-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonPlanelementsType }
     *     
     */
    public void setPersonPlanelements(PersonPlanelementsType value) {
        this.personPlanelements = value;
    }

}
