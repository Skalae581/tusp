
package de.his.ws.personaddressservice;

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
 *         &lt;element name="addressConfigurations" type="{http://www.his.de/ws/PersonAddressService}addressConfigurationsType"/&gt;
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
    "addressConfigurations"
})
@XmlRootElement(name = "getAddressConfigurationForPersonResponse")
public class GetAddressConfigurationForPersonResponse {

    @XmlElement(required = true)
    protected AddressConfigurationsType addressConfigurations;

    /**
     * Ruft den Wert der addressConfigurations-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AddressConfigurationsType }
     *     
     */
    public AddressConfigurationsType getAddressConfigurations() {
        return addressConfigurations;
    }

    /**
     * Legt den Wert der addressConfigurations-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressConfigurationsType }
     *     
     */
    public void setAddressConfigurations(AddressConfigurationsType value) {
        this.addressConfigurations = value;
    }

}
