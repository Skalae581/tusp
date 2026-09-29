
package de.his.ws.personservice;

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
 *         &lt;element name="academicDegrees" type="{http://www.his.de/ws/PersonService}academicDegreesType"/&gt;
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
    "academicDegrees"
})
@XmlRootElement(name = "readAllAcademicDegreesResponse")
public class ReadAllAcademicDegreesResponse {

    @XmlElement(required = true)
    protected AcademicDegreesType academicDegrees;

    /**
     * Ruft den Wert der academicDegrees-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AcademicDegreesType }
     *     
     */
    public AcademicDegreesType getAcademicDegrees() {
        return academicDegrees;
    }

    /**
     * Legt den Wert der academicDegrees-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AcademicDegreesType }
     *     
     */
    public void setAcademicDegrees(AcademicDegreesType value) {
        this.academicDegrees = value;
    }

}
