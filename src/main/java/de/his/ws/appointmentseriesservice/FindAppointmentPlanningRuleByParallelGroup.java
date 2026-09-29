
package de.his.ws.appointmentseriesservice;

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
 *         &lt;element name="parallelGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "parallelGroupId"
})
@XmlRootElement(name = "findAppointmentPlanningRuleByParallelGroup")
public class FindAppointmentPlanningRuleByParallelGroup {

    protected long parallelGroupId;

    /**
     * Ruft den Wert der parallelGroupId-Eigenschaft ab.
     * 
     */
    public long getParallelGroupId() {
        return parallelGroupId;
    }

    /**
     * Legt den Wert der parallelGroupId-Eigenschaft fest.
     * 
     */
    public void setParallelGroupId(long value) {
        this.parallelGroupId = value;
    }

}
