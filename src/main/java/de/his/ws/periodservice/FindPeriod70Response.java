
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
 *         &lt;element name="periodInfos" type="{http://www.his.de/ws/PeriodService}periodInfosType"/&gt;
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
    "periodInfos"
})
@XmlRootElement(name = "findPeriod70Response")
public class FindPeriod70Response {

    @XmlElement(required = true)
    protected PeriodInfosType periodInfos;

    /**
     * Ruft den Wert der periodInfos-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PeriodInfosType }
     *     
     */
    public PeriodInfosType getPeriodInfos() {
        return periodInfos;
    }

    /**
     * Legt den Wert der periodInfos-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodInfosType }
     *     
     */
    public void setPeriodInfos(PeriodInfosType value) {
        this.periodInfos = value;
    }

}
