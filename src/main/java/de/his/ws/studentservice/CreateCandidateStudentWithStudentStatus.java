
package de.his.ws.studentservice;

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
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="genderHiskey" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="courseOfStudy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="generateRegnumber" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="studentStatusHiskeyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "surname",
    "firstname",
    "genderHiskey",
    "courseOfStudy",
    "generateRegnumber",
    "studentStatusHiskeyId"
})
@XmlRootElement(name = "createCandidateStudentWithStudentStatus")
public class CreateCandidateStudentWithStudentStatus {

    @XmlElement(required = true)
    protected String surname;
    @XmlElement(required = true)
    protected String firstname;
    protected long genderHiskey;
    @XmlElement(required = true)
    protected String courseOfStudy;
    protected boolean generateRegnumber;
    protected long studentStatusHiskeyId;

    /**
     * Ruft den Wert der surname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Legt den Wert der surname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Ruft den Wert der firstname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Legt den Wert der firstname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Ruft den Wert der genderHiskey-Eigenschaft ab.
     * 
     */
    public long getGenderHiskey() {
        return genderHiskey;
    }

    /**
     * Legt den Wert der genderHiskey-Eigenschaft fest.
     * 
     */
    public void setGenderHiskey(long value) {
        this.genderHiskey = value;
    }

    /**
     * Ruft den Wert der courseOfStudy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    /**
     * Legt den Wert der courseOfStudy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseOfStudy(String value) {
        this.courseOfStudy = value;
    }

    /**
     * Ruft den Wert der generateRegnumber-Eigenschaft ab.
     * 
     */
    public boolean isGenerateRegnumber() {
        return generateRegnumber;
    }

    /**
     * Legt den Wert der generateRegnumber-Eigenschaft fest.
     * 
     */
    public void setGenerateRegnumber(boolean value) {
        this.generateRegnumber = value;
    }

    /**
     * Ruft den Wert der studentStatusHiskeyId-Eigenschaft ab.
     * 
     */
    public long getStudentStatusHiskeyId() {
        return studentStatusHiskeyId;
    }

    /**
     * Legt den Wert der studentStatusHiskeyId-Eigenschaft fest.
     * 
     */
    public void setStudentStatusHiskeyId(long value) {
        this.studentStatusHiskeyId = value;
    }

}
