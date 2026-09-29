
package de.his.ws.mimedataservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="targetType" type="{http://www.his.de/ws/MimedataService}targetTypeType"/&gt;
 *         &lt;element name="targetId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="category" type="{http://www.his.de/ws/MimedataService}categoryType"/&gt;
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
    "targetType",
    "targetId",
    "category"
})
@XmlRootElement(name = "readDocuments")
public class ReadDocuments {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TargetTypeType targetType;
    protected long targetId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CategoryType category;

    /**
     * Ruft den Wert der targetType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TargetTypeType }
     *     
     */
    public TargetTypeType getTargetType() {
        return targetType;
    }

    /**
     * Legt den Wert der targetType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetTypeType }
     *     
     */
    public void setTargetType(TargetTypeType value) {
        this.targetType = value;
    }

    /**
     * Ruft den Wert der targetId-Eigenschaft ab.
     * 
     */
    public long getTargetId() {
        return targetId;
    }

    /**
     * Legt den Wert der targetId-Eigenschaft fest.
     * 
     */
    public void setTargetId(long value) {
        this.targetId = value;
    }

    /**
     * Ruft den Wert der category-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CategoryType }
     *     
     */
    public CategoryType getCategory() {
        return category;
    }

    /**
     * Legt den Wert der category-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryType }
     *     
     */
    public void setCategory(CategoryType value) {
        this.category = value;
    }

}
