
package de.his.ws.studentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für Examplan complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Examplan"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateOfWork" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="examimport" type="{http://www.his.de/ws/StudentService}Examimport"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Examplan", propOrder = {
    "dateOfWork",
    "examimport"
})
public class Examplan {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfWork;
    @XmlElement(required = true)
    protected Examimport examimport;

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
     * Ruft den Wert der examimport-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Examimport }
     *     
     */
    public Examimport getExamimport() {
        return examimport;
    }

    /**
     * Legt den Wert der examimport-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Examimport }
     *     
     */
    public void setExamimport(Examimport value) {
        this.examimport = value;
    }

}
