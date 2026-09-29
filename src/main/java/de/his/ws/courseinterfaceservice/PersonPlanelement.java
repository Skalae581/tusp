
package de.his.ws.courseinterfaceservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * related responsible person for planelement
 * 
 * <p>Java-Klasse für PersonPlanelement complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PersonPlanelement"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="planelementId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="abstractPersonId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="sortorder" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonPlanelement", propOrder = {
    "planelementId",
    "abstractPersonId",
    "sortorder"
})
public class PersonPlanelement {

    protected long planelementId;
    protected long abstractPersonId;
    @XmlElement(required = true)
    protected BigInteger sortorder;

    /**
     * Ruft den Wert der planelementId-Eigenschaft ab.
     * 
     */
    public long getPlanelementId() {
        return planelementId;
    }

    /**
     * Legt den Wert der planelementId-Eigenschaft fest.
     * 
     */
    public void setPlanelementId(long value) {
        this.planelementId = value;
    }

    /**
     * Ruft den Wert der abstractPersonId-Eigenschaft ab.
     * 
     */
    public long getAbstractPersonId() {
        return abstractPersonId;
    }

    /**
     * Legt den Wert der abstractPersonId-Eigenschaft fest.
     * 
     */
    public void setAbstractPersonId(long value) {
        this.abstractPersonId = value;
    }

    /**
     * Ruft den Wert der sortorder-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSortorder() {
        return sortorder;
    }

    /**
     * Legt den Wert der sortorder-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSortorder(BigInteger value) {
        this.sortorder = value;
    }

}
