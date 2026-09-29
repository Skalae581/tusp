
package de.his.ws.courseinterfaceservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * related course-administrator for elearning-systems
 * 
 * <p>Java-Klasse für PersonExternal complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PersonExternal"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="planelementId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="abstractPersonId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonExternal", propOrder = {
    "planelementId",
    "abstractPersonId"
})
public class PersonExternal {

    @XmlElement(nillable = true)
    protected Long planelementId;
    protected long abstractPersonId;

    /**
     * Ruft den Wert der planelementId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlanelementId() {
        return planelementId;
    }

    /**
     * Legt den Wert der planelementId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlanelementId(Long value) {
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

}
