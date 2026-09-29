
package de.his.ws.courseinterfaceservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="planelementId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="workstatusIds" type="{http://www.his.de/ws/CourseInterfaceService}workstatusIdsType"/&gt;
 *         &lt;element name="cancellation" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="updatedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
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
    "planelementId",
    "workstatusIds",
    "cancellation",
    "updatedSince"
})
@XmlRootElement(name = "readPersonExamplanEnrollments")
public class ReadPersonExamplanEnrollments {

    protected long planelementId;
    @XmlElement(required = true, nillable = true)
    protected WorkstatusIdsType workstatusIds;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean cancellation;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedSince;

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
     * Ruft den Wert der workstatusIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link WorkstatusIdsType }
     *     
     */
    public WorkstatusIdsType getWorkstatusIds() {
        return workstatusIds;
    }

    /**
     * Legt den Wert der workstatusIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkstatusIdsType }
     *     
     */
    public void setWorkstatusIds(WorkstatusIdsType value) {
        this.workstatusIds = value;
    }

    /**
     * Ruft den Wert der cancellation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancellation() {
        return cancellation;
    }

    /**
     * Legt den Wert der cancellation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancellation(Boolean value) {
        this.cancellation = value;
    }

    /**
     * Ruft den Wert der updatedSince-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedSince() {
        return updatedSince;
    }

    /**
     * Legt den Wert der updatedSince-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedSince(XMLGregorianCalendar value) {
        this.updatedSince = value;
    }

}
