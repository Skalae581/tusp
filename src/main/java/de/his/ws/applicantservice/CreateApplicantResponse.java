
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
 *         &lt;element name="applicantDto" type="{http://www.his.de/ws/ApplicantService}ApplicantDto"/&gt;
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
    "applicantDto"
})
@XmlRootElement(name = "createApplicantResponse")
public class CreateApplicantResponse {

    @XmlElement(required = true)
    protected ApplicantDto applicantDto;

    /**
     * Ruft den Wert der applicantDto-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ApplicantDto }
     *     
     */
    public ApplicantDto getApplicantDto() {
        return applicantDto;
    }

    /**
     * Legt den Wert der applicantDto-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicantDto }
     *     
     */
    public void setApplicantDto(ApplicantDto value) {
        this.applicantDto = value;
    }

}
