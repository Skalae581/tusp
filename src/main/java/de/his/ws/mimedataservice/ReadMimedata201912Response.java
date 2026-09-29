
package de.his.ws.mimedataservice;

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
 *         &lt;element name="mimedata" type="{http://www.his.de/ws/MimedataService}Mimedata"/&gt;
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
    "mimedata"
})
@XmlRootElement(name = "readMimedata201912Response")
public class ReadMimedata201912Response {

    @XmlElement(required = true, nillable = true)
    protected Mimedata mimedata;

    /**
     * Ruft den Wert der mimedata-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Mimedata }
     *     
     */
    public Mimedata getMimedata() {
        return mimedata;
    }

    /**
     * Legt den Wert der mimedata-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Mimedata }
     *     
     */
    public void setMimedata(Mimedata value) {
        this.mimedata = value;
    }

}
