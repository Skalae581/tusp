
package de.his.ws.studentservice201812;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="courseOfStudyIds" type="{http://www.his.de/ws/StudentService201812}courseOfStudyIdsType"/&gt;
 *         &lt;element name="generateRegnumber" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="studentstatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="orgunit" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="enrollmentDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="currentEnrollmentdate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="termType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="studystatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="addresstag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="postaddress" type="{http://www.his.de/ws/StudentService201812}PostaddressSimple"/&gt;
 *         &lt;element name="healthInsuranceConfirmationStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="healthInsuranceCheckdate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="healthInsuranceMatching" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="healthInsuranceSmvparticipation" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "gender",
    "courseOfStudyIds",
    "generateRegnumber",
    "studentstatus",
    "orgunit",
    "enrollmentDate",
    "currentEnrollmentdate",
    "termType",
    "year",
    "studystatus",
    "addresstag",
    "postaddress",
    "healthInsuranceConfirmationStatus",
    "healthInsuranceCheckdate",
    "healthInsuranceMatching",
    "healthInsuranceSmvparticipation"
})
@XmlRootElement(name = "createCandidateStudent202012")
public class CreateCandidateStudent202012 {

    @XmlElement(required = true)
    protected String surname;
    @XmlElement(required = true)
    protected String firstname;
    @XmlElement(required = true)
    protected String gender;
    @XmlElement(required = true)
    protected CourseOfStudyIdsType courseOfStudyIds;
    protected boolean generateRegnumber;
    @XmlElement(required = true, nillable = true)
    protected String studentstatus;
    @XmlElement(required = true, nillable = true)
    protected String orgunit;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar enrollmentDate;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currentEnrollmentdate;
    @XmlElement(required = true, nillable = true)
    protected String termType;
    @XmlElement(required = true, nillable = true)
    protected BigInteger year;
    @XmlElement(required = true, nillable = true)
    protected String studystatus;
    @XmlElement(nillable = true)
    protected String addresstag;
    @XmlElement(required = true, nillable = true)
    protected PostaddressSimple postaddress;
    @XmlElement(required = true, nillable = true)
    protected String healthInsuranceConfirmationStatus;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar healthInsuranceCheckdate;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean healthInsuranceMatching;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean healthInsuranceSmvparticipation;

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
     * Ruft den Wert der gender-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Legt den Wert der gender-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Ruft den Wert der courseOfStudyIds-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CourseOfStudyIdsType }
     *     
     */
    public CourseOfStudyIdsType getCourseOfStudyIds() {
        return courseOfStudyIds;
    }

    /**
     * Legt den Wert der courseOfStudyIds-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CourseOfStudyIdsType }
     *     
     */
    public void setCourseOfStudyIds(CourseOfStudyIdsType value) {
        this.courseOfStudyIds = value;
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
     * Ruft den Wert der studentstatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentstatus() {
        return studentstatus;
    }

    /**
     * Legt den Wert der studentstatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentstatus(String value) {
        this.studentstatus = value;
    }

    /**
     * Ruft den Wert der orgunit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgunit() {
        return orgunit;
    }

    /**
     * Legt den Wert der orgunit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgunit(String value) {
        this.orgunit = value;
    }

    /**
     * Ruft den Wert der enrollmentDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEnrollmentDate() {
        return enrollmentDate;
    }

    /**
     * Legt den Wert der enrollmentDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEnrollmentDate(XMLGregorianCalendar value) {
        this.enrollmentDate = value;
    }

    /**
     * Ruft den Wert der currentEnrollmentdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCurrentEnrollmentdate() {
        return currentEnrollmentdate;
    }

    /**
     * Legt den Wert der currentEnrollmentdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCurrentEnrollmentdate(XMLGregorianCalendar value) {
        this.currentEnrollmentdate = value;
    }

    /**
     * Ruft den Wert der termType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermType() {
        return termType;
    }

    /**
     * Legt den Wert der termType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermType(String value) {
        this.termType = value;
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
     * Ruft den Wert der studystatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudystatus() {
        return studystatus;
    }

    /**
     * Legt den Wert der studystatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudystatus(String value) {
        this.studystatus = value;
    }

    /**
     * Ruft den Wert der addresstag-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddresstag() {
        return addresstag;
    }

    /**
     * Legt den Wert der addresstag-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddresstag(String value) {
        this.addresstag = value;
    }

    /**
     * Ruft den Wert der postaddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PostaddressSimple }
     *     
     */
    public PostaddressSimple getPostaddress() {
        return postaddress;
    }

    /**
     * Legt den Wert der postaddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PostaddressSimple }
     *     
     */
    public void setPostaddress(PostaddressSimple value) {
        this.postaddress = value;
    }

    /**
     * Ruft den Wert der healthInsuranceConfirmationStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthInsuranceConfirmationStatus() {
        return healthInsuranceConfirmationStatus;
    }

    /**
     * Legt den Wert der healthInsuranceConfirmationStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthInsuranceConfirmationStatus(String value) {
        this.healthInsuranceConfirmationStatus = value;
    }

    /**
     * Ruft den Wert der healthInsuranceCheckdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHealthInsuranceCheckdate() {
        return healthInsuranceCheckdate;
    }

    /**
     * Legt den Wert der healthInsuranceCheckdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHealthInsuranceCheckdate(XMLGregorianCalendar value) {
        this.healthInsuranceCheckdate = value;
    }

    /**
     * Ruft den Wert der healthInsuranceMatching-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHealthInsuranceMatching() {
        return healthInsuranceMatching;
    }

    /**
     * Legt den Wert der healthInsuranceMatching-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHealthInsuranceMatching(Boolean value) {
        this.healthInsuranceMatching = value;
    }

    /**
     * Ruft den Wert der healthInsuranceSmvparticipation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHealthInsuranceSmvparticipation() {
        return healthInsuranceSmvparticipation;
    }

    /**
     * Legt den Wert der healthInsuranceSmvparticipation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHealthInsuranceSmvparticipation(Boolean value) {
        this.healthInsuranceSmvparticipation = value;
    }

}
