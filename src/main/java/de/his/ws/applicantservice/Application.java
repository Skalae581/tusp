
package de.his.ws.applicantservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Application complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Application"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="termYear" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="termType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="externalInformation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="defaultRequestDataSet" type="{http://www.his.de/ws/ApplicantService}defaultRequestDataSetType"/&gt;
 *         &lt;element name="priorizedRequestDataSet" type="{http://www.his.de/ws/ApplicantService}priorizedRequestDataSetType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Application", propOrder = {
    "id",
    "term",
    "termYear",
    "termType",
    "remark",
    "externalInformation",
    "defaultRequestDataSet",
    "priorizedRequestDataSet"
})
public class Application {

    protected long id;
    @XmlElement(required = true)
    protected String term;
    @XmlElement(required = true, nillable = true)
    protected BigInteger termYear;
    @XmlElement(required = true)
    protected String termType;
    @XmlElement(required = true, nillable = true)
    protected String remark;
    @XmlElement(required = true, nillable = true)
    protected String externalInformation;
    @XmlElement(required = true, nillable = true)
    protected DefaultRequestDataSetType defaultRequestDataSet;
    @XmlElement(required = true, nillable = true)
    protected PriorizedRequestDataSetType priorizedRequestDataSet;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der term-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerm() {
        return term;
    }

    /**
     * Legt den Wert der term-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerm(String value) {
        this.term = value;
    }

    /**
     * Ruft den Wert der termYear-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTermYear() {
        return termYear;
    }

    /**
     * Legt den Wert der termYear-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTermYear(BigInteger value) {
        this.termYear = value;
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
     * Ruft den Wert der remark-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Legt den Wert der remark-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Ruft den Wert der externalInformation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalInformation() {
        return externalInformation;
    }

    /**
     * Legt den Wert der externalInformation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalInformation(String value) {
        this.externalInformation = value;
    }

    /**
     * Ruft den Wert der defaultRequestDataSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DefaultRequestDataSetType }
     *     
     */
    public DefaultRequestDataSetType getDefaultRequestDataSet() {
        return defaultRequestDataSet;
    }

    /**
     * Legt den Wert der defaultRequestDataSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultRequestDataSetType }
     *     
     */
    public void setDefaultRequestDataSet(DefaultRequestDataSetType value) {
        this.defaultRequestDataSet = value;
    }

    /**
     * Ruft den Wert der priorizedRequestDataSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PriorizedRequestDataSetType }
     *     
     */
    public PriorizedRequestDataSetType getPriorizedRequestDataSet() {
        return priorizedRequestDataSet;
    }

    /**
     * Legt den Wert der priorizedRequestDataSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PriorizedRequestDataSetType }
     *     
     */
    public void setPriorizedRequestDataSet(PriorizedRequestDataSetType value) {
        this.priorizedRequestDataSet = value;
    }

}
