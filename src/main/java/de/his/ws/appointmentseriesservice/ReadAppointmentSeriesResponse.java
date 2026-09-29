
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
 *         &lt;element name="appointmentSeries" type="{http://www.his.de/ws/AppointmentSeriesService}AppointmentSeries"/&gt;
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
    "appointmentSeries"
})
@XmlRootElement(name = "readAppointmentSeriesResponse")
public class ReadAppointmentSeriesResponse {

    @XmlElement(required = true)
    protected AppointmentSeries appointmentSeries;

    /**
     * Ruft den Wert der appointmentSeries-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AppointmentSeries }
     *     
     */
    public AppointmentSeries getAppointmentSeries() {
        return appointmentSeries;
    }

    /**
     * Legt den Wert der appointmentSeries-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentSeries }
     *     
     */
    public void setAppointmentSeries(AppointmentSeries value) {
        this.appointmentSeries = value;
    }

}
