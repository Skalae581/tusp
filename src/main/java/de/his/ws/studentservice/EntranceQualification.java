
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EntranceQualification complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EntranceQualification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="entranceQualificationType" type="{http://www.his.de/ws/StudentService}EntranceQualificationType"/&gt;
 *         &lt;element name="examplan" type="{http://www.his.de/ws/StudentService}Examplan"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntranceQualification", propOrder = {
    "id",
    "entranceQualificationType",
    "examplan"
})
public class EntranceQualification {

    protected long id;
    @XmlElement(required = true)
    protected EntranceQualificationType entranceQualificationType;
    @XmlElement(required = true)
    protected Examplan examplan;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der entranceQualificationType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EntranceQualificationType }
     *     
     */
    public EntranceQualificationType getEntranceQualificationType() {
        return entranceQualificationType;
    }

    /**
     * Legt den Wert der entranceQualificationType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EntranceQualificationType }
     *     
     */
    public void setEntranceQualificationType(EntranceQualificationType value) {
        this.entranceQualificationType = value;
    }

    /**
     * Ruft den Wert der examplan-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Examplan }
     *     
     */
    public Examplan getExamplan() {
        return examplan;
    }

    /**
     * Legt den Wert der examplan-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Examplan }
     *     
     */
    public void setExamplan(Examplan value) {
        this.examplan = value;
    }

}
