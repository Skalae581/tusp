
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
 *         &lt;element name="EntranceQualifications201712" type="{http://www.his.de/ws/StudentService}EntranceQualifications201712Type"/&gt;
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
    "entranceQualifications201712"
})
@XmlRootElement(name = "readHzbByPersonIdResponse")
public class ReadHzbByPersonIdResponse {

    @XmlElement(name = "EntranceQualifications201712", required = true)
    protected EntranceQualifications201712Type entranceQualifications201712;

    /**
     * Ruft den Wert der entranceQualifications201712-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EntranceQualifications201712Type }
     *     
     */
    public EntranceQualifications201712Type getEntranceQualifications201712() {
        return entranceQualifications201712;
    }

    /**
     * Legt den Wert der entranceQualifications201712-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EntranceQualifications201712Type }
     *     
     */
    public void setEntranceQualifications201712(EntranceQualifications201712Type value) {
        this.entranceQualifications201712 = value;
    }

}
