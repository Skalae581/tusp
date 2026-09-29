
package de.his.ws.courseinterfaceservice;

import java.math.BigInteger;
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
 *         &lt;element name="unitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="termyear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
    "unitId",
    "termTypeId",
    "termyear"
})
@XmlRootElement(name = "readPlanelementsForUnit")
public class ReadPlanelementsForUnit {

    protected long unitId;
    protected long termTypeId;
    @XmlElement(required = true)
    protected BigInteger termyear;

    /**
     * Ruft den Wert der unitId-Eigenschaft ab.
     * 
     */
    public long getUnitId() {
        return unitId;
    }

    /**
     * Legt den Wert der unitId-Eigenschaft fest.
     * 
     */
    public void setUnitId(long value) {
        this.unitId = value;
    }

    /**
     * Ruft den Wert der termTypeId-Eigenschaft ab.
     * 
     */
    public long getTermTypeId() {
        return termTypeId;
    }

    /**
     * Legt den Wert der termTypeId-Eigenschaft fest.
     * 
     */
    public void setTermTypeId(long value) {
        this.termTypeId = value;
    }

    /**
     * Ruft den Wert der termyear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTermyear() {
        return termyear;
    }

    /**
     * Legt den Wert der termyear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTermyear(BigInteger value) {
        this.termyear = value;
    }

}
