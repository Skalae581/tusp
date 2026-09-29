
package de.his.ws.keyvalueservice;

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
 *         &lt;element name="valuesXtd" type="{http://www.his.de/ws/KeyvalueService}valuesXtdType"/&gt;
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
    "valuesXtd"
})
@XmlRootElement(name = "getAllValidExtendedResponse")
public class GetAllValidExtendedResponse {

    @XmlElement(required = true)
    protected ValuesXtdType valuesXtd;

    /**
     * Ruft den Wert der valuesXtd-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ValuesXtdType }
     *     
     */
    public ValuesXtdType getValuesXtd() {
        return valuesXtd;
    }

    /**
     * Legt den Wert der valuesXtd-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuesXtdType }
     *     
     */
    public void setValuesXtd(ValuesXtdType value) {
        this.valuesXtd = value;
    }

}
