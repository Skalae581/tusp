
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
 *         &lt;element name="periodIds" type="{http://www.his.de/ws/PeriodService}periodIdsType"/&gt;
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
    "periodIds"
})
@XmlRootElement(name = "findPeriodResponse")
public class FindPeriodResponse {

    @XmlElement(required = true)
    protected PeriodIdsType periodIds;

    /**
     * Ruft den Wert der periodIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PeriodIdsType }
     *     
     */
    public PeriodIdsType getPeriodIds() {
        return periodIds;
    }

    /**
     * Legt den Wert der periodIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodIdsType }
     *     
     */
    public void setPeriodIds(PeriodIdsType value) {
        this.periodIds = value;
    }

}
