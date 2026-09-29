
package de.his.ws.appointmentseriesservice;

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
 *         &lt;element name="instructingLecturers" type="{http://www.his.de/ws/AppointmentSeriesService}instructingLecturersType"/&gt;
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
    "instructingLecturers"
})
@XmlRootElement(name = "readAssignedInstructingLecturersForSeriesResponse")
public class ReadAssignedInstructingLecturersForSeriesResponse {

    @XmlElement(required = true)
    protected InstructingLecturersType instructingLecturers;

    /**
     * Ruft den Wert der instructingLecturers-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InstructingLecturersType }
     *     
     */
    public InstructingLecturersType getInstructingLecturers() {
        return instructingLecturers;
    }

    /**
     * Legt den Wert der instructingLecturers-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InstructingLecturersType }
     *     
     */
    public void setInstructingLecturers(InstructingLecturersType value) {
        this.instructingLecturers = value;
    }

}
