
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
 *         &lt;element name="requestSubjectIdInfoList" type="{http://www.his.de/ws/ApplicantService}requestSubjectIdInfoListType"/&gt;
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
    "requestSubjectIdInfoList"
})
@XmlRootElement(name = "findRequestSubjectResponse")
public class FindRequestSubjectResponse {

    @XmlElement(required = true)
    protected RequestSubjectIdInfoListType requestSubjectIdInfoList;

    /**
     * Ruft den Wert der requestSubjectIdInfoList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RequestSubjectIdInfoListType }
     *     
     */
    public RequestSubjectIdInfoListType getRequestSubjectIdInfoList() {
        return requestSubjectIdInfoList;
    }

    /**
     * Legt den Wert der requestSubjectIdInfoList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestSubjectIdInfoListType }
     *     
     */
    public void setRequestSubjectIdInfoList(RequestSubjectIdInfoListType value) {
        this.requestSubjectIdInfoList = value;
    }

}
