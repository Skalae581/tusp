
package de.his.ws.studentservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ExamimportStudentexternal complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ExamimportStudentexternal"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/StudentService}GeneratedDtoBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="foreignGrade" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
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
@XmlType(name = "ExamimportStudentexternal", propOrder = {
    "foreignGrade",
    "examplan"
})
public class ExamimportStudentexternal
    extends GeneratedDtoBase
{

    @XmlElement(required = true, nillable = true)
    protected BigDecimal foreignGrade;
    @XmlElement(required = true)
    protected Examplan70 examplan;

    /**
     * Ruft den Wert der foreignGrade-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getForeignGrade() {
        return foreignGrade;
    }

    /**
     * Legt den Wert der foreignGrade-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setForeignGrade(BigDecimal value) {
        this.foreignGrade = value;
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
