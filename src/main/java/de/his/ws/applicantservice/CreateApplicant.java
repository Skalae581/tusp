
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
 *         &lt;element name="applicantPerson" type="{http://www.his.de/ws/ApplicantService}ApplicantPerson"/&gt;
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
    "applicantPerson"
})
@XmlRootElement(name = "createApplicant")
public class CreateApplicant {

    @XmlElement(required = true)
    protected ApplicantPerson applicantPerson;

    /**
     * Ruft den Wert der applicantPerson-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApplicantPerson }
     *     
     */
    public ApplicantPerson getApplicantPerson() {
        return applicantPerson;
    }

    /**
     * Legt den Wert der applicantPerson-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicantPerson }
     *     
     */
    public void setApplicantPerson(ApplicantPerson value) {
        this.applicantPerson = value;
    }

}
