
package de.his.ws.studentservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für Examplan70 complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Examplan70"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateOfWork" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="termSegment" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="defaultExamrelation" type="{http://www.his.de/ws/StudentService}ExamrelationDto"/&gt;
 *         &lt;element name="examimport" type="{http://www.his.de/ws/StudentService}Examimport70"/&gt;
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="unitId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Examplan70", propOrder = {
    "dateOfWork",
    "termSegment",
    "year",
    "defaultExamrelation",
    "examimport",
    "personId",
    "unitId"
})
public class Examplan70
    extends GeneratedDtoBase
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfWork;
    @XmlElement(required = true, nillable = true)
    protected BigInteger termSegment;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    @XmlElement(required = true)
    protected ExamrelationDto defaultExamrelation;
    @XmlElement(required = true, nillable = true)
    protected Examimport70 examimport;
    protected long personId;
    protected long unitId;

    /**
     * Ruft den Wert der dateOfWork-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfWork() {
        return dateOfWork;
    }

    /**
     * Legt den Wert der dateOfWork-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfWork(XMLGregorianCalendar value) {
        this.dateOfWork = value;
    }

    /**
     * Ruft den Wert der termSegment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTermSegment() {
        return termSegment;
    }

    /**
     * Legt den Wert der termSegment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTermSegment(BigInteger value) {
        this.termSegment = value;
    }

    /**
     * Ruft den Wert der year-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYear() {
        return year;
    }

    /**
     * Legt den Wert der year-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYear(BigInteger value) {
        this.year = value;
    }

    /**
     * Ruft den Wert der defaultExamrelation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExamrelationDto }
     *     
     */
    public ExamrelationDto getDefaultExamrelation() {
        return defaultExamrelation;
    }

    /**
     * Legt den Wert der defaultExamrelation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExamrelationDto }
     *     
     */
    public void setDefaultExamrelation(ExamrelationDto value) {
        this.defaultExamrelation = value;
    }

    /**
     * Ruft den Wert der examimport-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Examimport70 }
     *     
     */
    public Examimport70 getExamimport() {
        return examimport;
    }

    /**
     * Legt den Wert der examimport-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Examimport70 }
     *     
     */
    public void setExamimport(Examimport70 value) {
        this.examimport = value;
    }

    /**
     * Ruft den Wert der personId-Eigenschaft ab.
     * 
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Legt den Wert der personId-Eigenschaft fest.
     * 
     */
    public void setPersonId(long value) {
        this.personId = value;
    }

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

}
