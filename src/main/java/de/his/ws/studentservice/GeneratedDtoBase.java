
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für GeneratedDtoBase complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="GeneratedDtoBase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}DtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lockVersion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="loadTime" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneratedDtoBase", propOrder = {
    "lockVersion",
    "loadTime"
})
@XmlSeeAlso({
    StudyBeforeDto.class,
    EntranceQualificationDto.class,
    StudentexternalDto.class,
    StayAbroadDto.class,
    StudentFunctionlockDto.class,
    ExamrelationDto.class,
    ExamimportStudentexternal.class,
    Examimport70 .class,
    DegreeProgramBeforeDto.class,
    Examplan70 .class,
    DegreeProgramProgressBeforeDto.class,
    ValueDtoBase.class
})
public class GeneratedDtoBase
    extends DtoBase
{

    @XmlElement(nillable = true)
    protected Long lockVersion;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar loadTime;

    /**
     * Ruft den Wert der lockVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLockVersion() {
        return lockVersion;
    }

    /**
     * Legt den Wert der lockVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLockVersion(Long value) {
        this.lockVersion = value;
    }

    /**
     * Ruft den Wert der loadTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLoadTime() {
        return loadTime;
    }

    /**
     * Legt den Wert der loadTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLoadTime(XMLGregorianCalendar value) {
        this.loadTime = value;
    }

}
