
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
 *         &lt;element name="planelementId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="editingStatusId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "planelementId",
    "editingStatusId"
})
@XmlRootElement(name = "setPlanelementEditingStatus")
public class SetPlanelementEditingStatus {

    protected long planelementId;
    protected long editingStatusId;

    /**
     * Ruft den Wert der planelementId-Eigenschaft ab.
     * 
     */
    public long getPlanelementId() {
        return planelementId;
    }

    /**
     * Legt den Wert der planelementId-Eigenschaft fest.
     * 
     */
    public void setPlanelementId(long value) {
        this.planelementId = value;
    }

    /**
     * Ruft den Wert der editingStatusId-Eigenschaft ab.
     * 
     */
    public long getEditingStatusId() {
        return editingStatusId;
    }

    /**
     * Legt den Wert der editingStatusId-Eigenschaft fest.
     * 
     */
    public void setEditingStatusId(long value) {
        this.editingStatusId = value;
    }

}
