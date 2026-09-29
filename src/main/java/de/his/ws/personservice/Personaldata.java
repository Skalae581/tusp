
package de.his.ws.personservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Personaldata complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Personaldata"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="workplaceDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="academicCareer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="professionalLife" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="researchProjects" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="practiceCooperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="publications" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="administrationFunctions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Personaldata", propOrder = {
    "personId",
    "workplaceDescription",
    "academicCareer",
    "professionalLife",
    "researchProjects",
    "practiceCooperation",
    "publications",
    "administrationFunctions"
})
public class Personaldata {

    protected long personId;
    @XmlElement(nillable = true)
    protected String workplaceDescription;
    @XmlElement(nillable = true)
    protected String academicCareer;
    @XmlElement(nillable = true)
    protected String professionalLife;
    @XmlElement(nillable = true)
    protected String researchProjects;
    @XmlElement(nillable = true)
    protected String practiceCooperation;
    @XmlElement(nillable = true)
    protected String publications;
    @XmlElement(nillable = true)
    protected String administrationFunctions;

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
     * Ruft den Wert der workplaceDescription-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceDescription() {
        return workplaceDescription;
    }

    /**
     * Legt den Wert der workplaceDescription-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceDescription(String value) {
        this.workplaceDescription = value;
    }

    /**
     * Ruft den Wert der academicCareer-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicCareer() {
        return academicCareer;
    }

    /**
     * Legt den Wert der academicCareer-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicCareer(String value) {
        this.academicCareer = value;
    }

    /**
     * Ruft den Wert der professionalLife-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfessionalLife() {
        return professionalLife;
    }

    /**
     * Legt den Wert der professionalLife-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfessionalLife(String value) {
        this.professionalLife = value;
    }

    /**
     * Ruft den Wert der researchProjects-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResearchProjects() {
        return researchProjects;
    }

    /**
     * Legt den Wert der researchProjects-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResearchProjects(String value) {
        this.researchProjects = value;
    }

    /**
     * Ruft den Wert der practiceCooperation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPracticeCooperation() {
        return practiceCooperation;
    }

    /**
     * Legt den Wert der practiceCooperation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPracticeCooperation(String value) {
        this.practiceCooperation = value;
    }

    /**
     * Ruft den Wert der publications-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublications() {
        return publications;
    }

    /**
     * Legt den Wert der publications-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublications(String value) {
        this.publications = value;
    }

    /**
     * Ruft den Wert der administrationFunctions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministrationFunctions() {
        return administrationFunctions;
    }

    /**
     * Legt den Wert der administrationFunctions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministrationFunctions(String value) {
        this.administrationFunctions = value;
    }

}
