
package de.his.ws.periodservice;

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
 *         &lt;element name="periods" type="{http://www.his.de/ws/PeriodService}periodsType"/&gt;
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
    "periods"
})
@XmlRootElement(name = "getActivePeriodsByTypeResponse")
public class GetActivePeriodsByTypeResponse {

    @XmlElement(required = true, nillable = true)
    protected PeriodsType periods;

    /**
     * Ruft den Wert der periods-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PeriodsType }
     *     
     */
    public PeriodsType getPeriods() {
        return periods;
    }

    /**
     * Legt den Wert der periods-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodsType }
     *     
     */
    public void setPeriods(PeriodsType value) {
        this.periods = value;
    }

}
