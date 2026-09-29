
package de.his.ws.keyvalueservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für valueClassDescriptionsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="valueClassDescriptionsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="valueClassDescription" type="{http://www.his.de/ws/KeyvalueService}ValueClassDescription" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valueClassDescriptionsType", propOrder = {
    "valueClassDescription"
})
public class ValueClassDescriptionsType {

    protected List<ValueClassDescription> valueClassDescription;

    /**
     * Gets the value of the valueClassDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueClassDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueClassDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueClassDescription }
     * 
     * 
     */
    public List<ValueClassDescription> getValueClassDescription() {
        if (valueClassDescription == null) {
            valueClassDescription = new ArrayList<ValueClassDescription>();
        }
        return this.valueClassDescription;
    }

}
