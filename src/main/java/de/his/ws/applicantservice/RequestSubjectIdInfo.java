
package de.his.ws.applicantservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Return values of findRequestSubject
 * 
 * <p>Java-Klasse für RequestSubjectIdInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RequestSubjectIdInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestSubjectId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="admissionPackageId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestSubjectIdInfo", propOrder = {
    "requestSubjectId",
    "admissionPackageId"
})
public class RequestSubjectIdInfo {

    protected long requestSubjectId;
    protected long admissionPackageId;

    /**
     * Ruft den Wert der requestSubjectId-Eigenschaft ab.
     * 
     */
    public long getRequestSubjectId() {
        return requestSubjectId;
    }

    /**
     * Legt den Wert der requestSubjectId-Eigenschaft fest.
     * 
     */
    public void setRequestSubjectId(long value) {
        this.requestSubjectId = value;
    }

    /**
     * Ruft den Wert der admissionPackageId-Eigenschaft ab.
     * 
     */
    public long getAdmissionPackageId() {
        return admissionPackageId;
    }

    /**
     * Legt den Wert der admissionPackageId-Eigenschaft fest.
     * 
     */
    public void setAdmissionPackageId(long value) {
        this.admissionPackageId = value;
    }

}
