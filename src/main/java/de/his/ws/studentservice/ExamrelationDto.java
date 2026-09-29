
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ExamrelationDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ExamrelationDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="unitrelationtypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="workstatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExamrelationDto", propOrder = {
    "unitrelationtypeId",
    "workstatusId"
})
public class ExamrelationDto
    extends GeneratedDtoBase
{

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long unitrelationtypeId;
    protected long workstatusId;

    /**
     * Ruft den Wert der unitrelationtypeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUnitrelationtypeId() {
        return unitrelationtypeId;
    }

    /**
     * Legt den Wert der unitrelationtypeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUnitrelationtypeId(Long value) {
        this.unitrelationtypeId = value;
    }

    /**
     * Ruft den Wert der workstatusId-Eigenschaft ab.
     * 
     */
    public long getWorkstatusId() {
        return workstatusId;
    }

    /**
     * Legt den Wert der workstatusId-Eigenschaft fest.
     * 
     */
    public void setWorkstatusId(long value) {
        this.workstatusId = value;
    }

}
