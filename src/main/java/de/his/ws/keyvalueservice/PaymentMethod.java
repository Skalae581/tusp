
package de.his.ws.keyvalueservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für PaymentMethod complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PaymentMethod"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.his.de/ws/KeyvalueService}AbstractKeyValue"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="incomingPaymentsAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="outgoingPaymentsAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentMethod", propOrder = {
    "incomingPaymentsAllowed",
    "outgoingPaymentsAllowed"
})
public class PaymentMethod
    extends AbstractKeyValue
{

    @XmlElement(nillable = true)
    protected Boolean incomingPaymentsAllowed;
    @XmlElement(nillable = true)
    protected Boolean outgoingPaymentsAllowed;

    /**
     * Ruft den Wert der incomingPaymentsAllowed-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncomingPaymentsAllowed() {
        return incomingPaymentsAllowed;
    }

    /**
     * Legt den Wert der incomingPaymentsAllowed-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncomingPaymentsAllowed(Boolean value) {
        this.incomingPaymentsAllowed = value;
    }

    /**
     * Ruft den Wert der outgoingPaymentsAllowed-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOutgoingPaymentsAllowed() {
        return outgoingPaymentsAllowed;
    }

    /**
     * Legt den Wert der outgoingPaymentsAllowed-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOutgoingPaymentsAllowed(Boolean value) {
        this.outgoingPaymentsAllowed = value;
    }

}
