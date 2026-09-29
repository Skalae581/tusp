
package de.his.ws.applicantservice;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für Request complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Request"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="requestNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="requestStatusId" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="receiptDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="inboxDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="requestSubjectDataSet" type="{http://www.his.de/ws/ApplicantService}requestSubjectDataSetType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", propOrder = {
    "id",
    "requestNumber",
    "requestStatusId",
    "receiptDate",
    "inboxDate",
    "requestSubjectDataSet"
})
public class Request {

    protected long id;
    @XmlElement(required = true)
    protected BigInteger requestNumber;
    @XmlElement(required = true)
    protected BigInteger requestStatusId;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar receiptDate;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inboxDate;
    @XmlElement(required = true, nillable = true)
    protected RequestSubjectDataSetType requestSubjectDataSet;

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
     * Ruft den Wert der requestNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRequestNumber() {
        return requestNumber;
    }

    /**
     * Legt den Wert der requestNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRequestNumber(BigInteger value) {
        this.requestNumber = value;
    }

    /**
     * Ruft den Wert der requestStatusId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRequestStatusId() {
        return requestStatusId;
    }

    /**
     * Legt den Wert der requestStatusId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRequestStatusId(BigInteger value) {
        this.requestStatusId = value;
    }

    /**
     * Ruft den Wert der receiptDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReceiptDate() {
        return receiptDate;
    }

    /**
     * Legt den Wert der receiptDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReceiptDate(XMLGregorianCalendar value) {
        this.receiptDate = value;
    }

    /**
     * Ruft den Wert der inboxDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInboxDate() {
        return inboxDate;
    }

    /**
     * Legt den Wert der inboxDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInboxDate(XMLGregorianCalendar value) {
        this.inboxDate = value;
    }

    /**
     * Ruft den Wert der requestSubjectDataSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RequestSubjectDataSetType }
     *     
     */
    public RequestSubjectDataSetType getRequestSubjectDataSet() {
        return requestSubjectDataSet;
    }

    /**
     * Legt den Wert der requestSubjectDataSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestSubjectDataSetType }
     *     
     */
    public void setRequestSubjectDataSet(RequestSubjectDataSetType value) {
        this.requestSubjectDataSet = value;
    }

}
