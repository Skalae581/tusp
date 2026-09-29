
package de.his.ws.facilityservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EquipmentQuantity complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EquipmentQuantity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="equipmentValueId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquipmentQuantity", propOrder = {
    "quantity",
    "equipmentValueId"
})
public class EquipmentQuantity {

    protected long quantity;
    protected long equipmentValueId;

    /**
     * Ruft den Wert der quantity-Eigenschaft ab.
     * 
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Legt den Wert der quantity-Eigenschaft fest.
     * 
     */
    public void setQuantity(long value) {
        this.quantity = value;
    }

    /**
     * Ruft den Wert der equipmentValueId-Eigenschaft ab.
     * 
     */
    public long getEquipmentValueId() {
        return equipmentValueId;
    }

    /**
     * Legt den Wert der equipmentValueId-Eigenschaft fest.
     * 
     */
    public void setEquipmentValueId(long value) {
        this.equipmentValueId = value;
    }

}
