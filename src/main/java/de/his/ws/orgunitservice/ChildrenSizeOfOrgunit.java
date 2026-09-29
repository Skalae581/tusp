
package de.his.ws.orgunitservice;

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
 *         &lt;element name="lid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="date" type="{http://www.his.de/ws/OrgUnitService}VersionDateRange"/&gt;
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
    "lid",
    "date"
})
@XmlRootElement(name = "childrenSizeOfOrgunit")
public class ChildrenSizeOfOrgunit {

    protected long lid;
    @XmlElement(required = true, nillable = true)
    protected VersionDateRange date;

    /**
     * Ruft den Wert der lid-Eigenschaft ab.
     * 
     */
    public long getLid() {
        return lid;
    }

    /**
     * Legt den Wert der lid-Eigenschaft fest.
     * 
     */
    public void setLid(long value) {
        this.lid = value;
    }

    /**
     * Ruft den Wert der date-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VersionDateRange }
     *     
     */
    public VersionDateRange getDate() {
        return date;
    }

    /**
     * Legt den Wert der date-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionDateRange }
     *     
     */
    public void setDate(VersionDateRange value) {
        this.date = value;
    }

}
