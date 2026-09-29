
package de.his.ws.studentservice;

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
 *         &lt;element name="eq" type="{http://www.his.de/ws/StudentService}EntranceQualificationDto"/&gt;
 *         &lt;element name="ausinland" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "eq",
    "ausinland"
})
@XmlRootElement(name = "saveHZB")
public class SaveHZB {

    @XmlElement(required = true)
    protected EntranceQualificationDto eq;
    protected long ausinland;

    /**
     * Ruft den Wert der eq-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EntranceQualificationDto }
     *     
     */
    public EntranceQualificationDto getEq() {
        return eq;
    }

    /**
     * Legt den Wert der eq-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EntranceQualificationDto }
     *     
     */
    public void setEq(EntranceQualificationDto value) {
        this.eq = value;
    }

    /**
     * Ruft den Wert der ausinland-Eigenschaft ab.
     * 
     */
    public long getAusinland() {
        return ausinland;
    }

    /**
     * Legt den Wert der ausinland-Eigenschaft fest.
     * 
     */
    public void setAusinland(long value) {
        this.ausinland = value;
    }

}
