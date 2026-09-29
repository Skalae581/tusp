
package de.his.ws.periodservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="hisKeyId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="ignoreDefault" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
    "hisKeyId",
    "ignoreDefault",
    "date"
})
@XmlRootElement(name = "getActivePeriodDtoByType")
public class GetActivePeriodDtoByType {

    protected long hisKeyId;
    protected boolean ignoreDefault;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;

    /**
     * Ruft den Wert der hisKeyId-Eigenschaft ab.
     * 
     */
    public long getHisKeyId() {
        return hisKeyId;
    }

    /**
     * Legt den Wert der hisKeyId-Eigenschaft fest.
     * 
     */
    public void setHisKeyId(long value) {
        this.hisKeyId = value;
    }

    /**
     * Ruft den Wert der ignoreDefault-Eigenschaft ab.
     * 
     */
    public boolean isIgnoreDefault() {
        return ignoreDefault;
    }

    /**
     * Legt den Wert der ignoreDefault-Eigenschaft fest.
     * 
     */
    public void setIgnoreDefault(boolean value) {
        this.ignoreDefault = value;
    }

    /**
     * Ruft den Wert der date-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Legt den Wert der date-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

}
