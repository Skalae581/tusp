
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
 *         &lt;element name="valueClassDescriptions" type="{http://www.his.de/ws/KeyvalueService}valueClassDescriptionsType"/&gt;
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
    "valueClassDescriptions"
})
@XmlRootElement(name = "getAllValueClassesResponse")
public class GetAllValueClassesResponse {

    @XmlElement(required = true)
    protected ValueClassDescriptionsType valueClassDescriptions;

    /**
     * Ruft den Wert der valueClassDescriptions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ValueClassDescriptionsType }
     *     
     */
    public ValueClassDescriptionsType getValueClassDescriptions() {
        return valueClassDescriptions;
    }

    /**
     * Legt den Wert der valueClassDescriptions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueClassDescriptionsType }
     *     
     */
    public void setValueClassDescriptions(ValueClassDescriptionsType value) {
        this.valueClassDescriptions = value;
    }

}
