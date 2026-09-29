
package de.his.ws.applicantservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="requestSubjectId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "requestSubjectId"
})
@XmlRootElement(name = "readApplicationContent")
public class ReadApplicationContent {

    protected long requestSubjectId;

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

}
