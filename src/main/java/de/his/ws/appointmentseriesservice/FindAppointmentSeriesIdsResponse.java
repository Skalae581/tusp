
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
 *         &lt;element name="plannedDatesIds" type="{http://www.his.de/ws/AppointmentSeriesService}plannedDatesIdsType"/&gt;
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
    "plannedDatesIds"
})
@XmlRootElement(name = "findAppointmentSeriesIdsResponse")
public class FindAppointmentSeriesIdsResponse {

    @XmlElement(required = true)
    protected PlannedDatesIdsType plannedDatesIds;

    /**
     * Ruft den Wert der plannedDatesIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PlannedDatesIdsType }
     *     
     */
    public PlannedDatesIdsType getPlannedDatesIds() {
        return plannedDatesIds;
    }

    /**
     * Legt den Wert der plannedDatesIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PlannedDatesIdsType }
     *     
     */
    public void setPlannedDatesIds(PlannedDatesIdsType value) {
        this.plannedDatesIds = value;
    }

}
