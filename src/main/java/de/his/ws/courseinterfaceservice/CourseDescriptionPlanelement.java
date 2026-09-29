
package de.his.ws.courseinterfaceservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * planelement description with semester independent description texts
 * 
 * <p>Java-Klasse für CourseDescriptionPlanelement complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CourseDescriptionPlanelement"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contents" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="courseAchievement" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="learningTarget" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="literature" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="objectiveQualification" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="recommendedRequirement" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourseDescriptionPlanelement", propOrder = {
    "contents",
    "courseAchievement",
    "learningTarget",
    "literature",
    "objectiveQualification",
    "recommendedRequirement"
})
@XmlSeeAlso({
    CourseDescription.class
})
public class CourseDescriptionPlanelement {

    @XmlElement(required = true, nillable = true)
    protected String contents;
    @XmlElement(required = true, nillable = true)
    protected String courseAchievement;
    @XmlElement(required = true, nillable = true)
    protected String learningTarget;
    @XmlElement(required = true, nillable = true)
    protected String literature;
    @XmlElement(required = true, nillable = true)
    protected String objectiveQualification;
    @XmlElement(required = true, nillable = true)
    protected String recommendedRequirement;

    /**
     * Ruft den Wert der contents-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContents() {
        return contents;
    }

    /**
     * Legt den Wert der contents-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContents(String value) {
        this.contents = value;
    }

    /**
     * Ruft den Wert der courseAchievement-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseAchievement() {
        return courseAchievement;
    }

    /**
     * Legt den Wert der courseAchievement-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseAchievement(String value) {
        this.courseAchievement = value;
    }

    /**
     * Ruft den Wert der learningTarget-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLearningTarget() {
        return learningTarget;
    }

    /**
     * Legt den Wert der learningTarget-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLearningTarget(String value) {
        this.learningTarget = value;
    }

    /**
     * Ruft den Wert der literature-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiterature() {
        return literature;
    }

    /**
     * Legt den Wert der literature-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiterature(String value) {
        this.literature = value;
    }

    /**
     * Ruft den Wert der objectiveQualification-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectiveQualification() {
        return objectiveQualification;
    }

    /**
     * Legt den Wert der objectiveQualification-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectiveQualification(String value) {
        this.objectiveQualification = value;
    }

    /**
     * Ruft den Wert der recommendedRequirement-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecommendedRequirement() {
        return recommendedRequirement;
    }

    /**
     * Legt den Wert der recommendedRequirement-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommendedRequirement(String value) {
        this.recommendedRequirement = value;
    }

}
