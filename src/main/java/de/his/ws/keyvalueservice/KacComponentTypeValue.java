
package de.his.ws.keyvalueservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für KacComponentTypeValue complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="KacComponentTypeValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractKeyValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="kacDeviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="kacDeviceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KacComponentTypeValue", propOrder = {
    "kacDeviceType",
    "kacDeviceName"
})
public class KacComponentTypeValue
    extends AbstractKeyValue
{

    @XmlElement(nillable = true)
    protected String kacDeviceType;
    @XmlElement(nillable = true)
    protected String kacDeviceName;

    /**
     * Ruft den Wert der kacDeviceType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKacDeviceType() {
        return kacDeviceType;
    }

    /**
     * Legt den Wert der kacDeviceType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKacDeviceType(String value) {
        this.kacDeviceType = value;
    }

    /**
     * Ruft den Wert der kacDeviceName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKacDeviceName() {
        return kacDeviceName;
    }

    /**
     * Legt den Wert der kacDeviceName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKacDeviceName(String value) {
        this.kacDeviceName = value;
    }

}
