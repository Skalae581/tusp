
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
 *         &lt;element name="periodInfoWithConditionInfoList" type="{http://www.his.de/ws/PeriodService}periodInfoWithConditionInfoListType"/&gt;
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
    "periodInfoWithConditionInfoList"
})
@XmlRootElement(name = "findPeriodAndConditionInfoResponse")
public class FindPeriodAndConditionInfoResponse {

    @XmlElement(required = true)
    protected PeriodInfoWithConditionInfoListType periodInfoWithConditionInfoList;

    /**
     * Ruft den Wert der periodInfoWithConditionInfoList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PeriodInfoWithConditionInfoListType }
     *     
     */
    public PeriodInfoWithConditionInfoListType getPeriodInfoWithConditionInfoList() {
        return periodInfoWithConditionInfoList;
    }

    /**
     * Legt den Wert der periodInfoWithConditionInfoList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodInfoWithConditionInfoListType }
     *     
     */
    public void setPeriodInfoWithConditionInfoList(PeriodInfoWithConditionInfoListType value) {
        this.periodInfoWithConditionInfoList = value;
    }

}
