
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für DegreeProgramBeforeDto complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DegreeProgramBeforeDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="degreeProgramProgressBefores" type="{http://www.his.de/ws/StudentService}degreeProgramProgressBeforesType"/&gt;
 *         &lt;element name="degreeId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="degreeObjId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="examimport" type="{http://www.his.de/ws/StudentService}ExamimportStudentexternal"/&gt;
 *         &lt;element name="formOfStudiesId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="studentexternalId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DegreeProgramBeforeDto", propOrder = {
    "createdAt",
    "updatedAt",
    "degreeProgramProgressBefores",
    "degreeId",
    "degreeObjId",
    "examimport",
    "formOfStudiesId",
    "studentexternalId"
})
public class DegreeProgramBeforeDto
    extends GeneratedDtoBase
{

    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar createdAt;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar updatedAt;
    @XmlElement(required = true, nillable = true)
    protected DegreeProgramProgressBeforesType degreeProgramProgressBefores;
    protected long degreeId;
    protected long degreeObjId;
    @XmlElement(required = true, nillable = true)
    protected ExamimportStudentexternal examimport;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long formOfStudiesId;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long studentexternalId;

    /**
     * Ruft den Wert der createdAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedAt() {
        return createdAt;
    }

    /**
     * Legt den Wert der createdAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedAt(XMLGregorianCalendar value) {
        this.createdAt = value;
    }

    /**
     * Ruft den Wert der updatedAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Legt den Wert der updatedAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedAt(XMLGregorianCalendar value) {
        this.updatedAt = value;
    }

    /**
     * Ruft den Wert der degreeProgramProgressBefores-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DegreeProgramProgressBeforesType }
     *     
     */
    public DegreeProgramProgressBeforesType getDegreeProgramProgressBefores() {
        return degreeProgramProgressBefores;
    }

    /**
     * Legt den Wert der degreeProgramProgressBefores-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DegreeProgramProgressBeforesType }
     *     
     */
    public void setDegreeProgramProgressBefores(DegreeProgramProgressBeforesType value) {
        this.degreeProgramProgressBefores = value;
    }

    /**
     * Ruft den Wert der degreeId-Eigenschaft ab.
     * 
     */
    public long getDegreeId() {
        return degreeId;
    }

    /**
     * Legt den Wert der degreeId-Eigenschaft fest.
     * 
     */
    public void setDegreeId(long value) {
        this.degreeId = value;
    }

    /**
     * Ruft den Wert der degreeObjId-Eigenschaft ab.
     * 
     */
    public long getDegreeObjId() {
        return degreeObjId;
    }

    /**
     * Legt den Wert der degreeObjId-Eigenschaft fest.
     * 
     */
    public void setDegreeObjId(long value) {
        this.degreeObjId = value;
    }

    /**
     * Ruft den Wert der examimport-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExamimportStudentexternal }
     *     
     */
    public ExamimportStudentexternal getExamimport() {
        return examimport;
    }

    /**
     * Legt den Wert der examimport-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExamimportStudentexternal }
     *     
     */
    public void setExamimport(ExamimportStudentexternal value) {
        this.examimport = value;
    }

    /**
     * Ruft den Wert der formOfStudiesId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFormOfStudiesId() {
        return formOfStudiesId;
    }

    /**
     * Legt den Wert der formOfStudiesId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFormOfStudiesId(Long value) {
        this.formOfStudiesId = value;
    }

    /**
     * Ruft den Wert der studentexternalId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStudentexternalId() {
        return studentexternalId;
    }

    /**
     * Legt den Wert der studentexternalId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStudentexternalId(Long value) {
        this.studentexternalId = value;
    }

}
