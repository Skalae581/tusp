
package de.his.ws.applicantservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="applicant" type="{http://www.his.de/ws/ApplicantService}Applicant"/&gt;
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
    "applicant"
})
@XmlRootElement(name = "readApplicantByIdResponse")
public class ReadApplicantByIdResponse {

    @XmlElement(required = true)
    protected Applicant applicant;

    /**
     * Ruft den Wert der applicant-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Applicant }
     *     
     */
    public Applicant getApplicant() {
        return applicant;
    }

    /**
     * Legt den Wert der applicant-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Applicant }
     *     
     */
    public void setApplicant(Applicant value) {
        this.applicant = value;
    }

}
