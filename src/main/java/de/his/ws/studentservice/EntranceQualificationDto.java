
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EntranceQualificationDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EntranceQualificationDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entranceQualificationTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="examplan" type="{http://www.his.de/ws/StudentService}Examplan70"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntranceQualificationDto", propOrder = {
    "entranceQualificationTypeId",
    "examplan"
})
public class EntranceQualificationDto
    extends GeneratedDtoBase
{

    protected long entranceQualificationTypeId;
    @XmlElement(required = true)
    protected Examplan70 examplan;

    /**
     * Ruft den Wert der entranceQualificationTypeId-Eigenschaft ab.
     * 
     */
    public long getEntranceQualificationTypeId() {
        return entranceQualificationTypeId;
    }

    /**
     * Legt den Wert der entranceQualificationTypeId-Eigenschaft fest.
     * 
     */
    public void setEntranceQualificationTypeId(long value) {
        this.entranceQualificationTypeId = value;
    }

    /**
     * Ruft den Wert der examplan-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Examplan70 }
     *     
     */
    public Examplan70 getExamplan() {
        return examplan;
    }

    /**
     * Legt den Wert der examplan-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Examplan70 }
     *     
     */
    public void setExamplan(Examplan70 value) {
        this.examplan = value;
    }

}
