
package de.his.ws.studentservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für degreePrograms70Type complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="degreePrograms70Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="degreeProgram70" type="{http://www.his.de/ws/StudentService}DegreeProgramDto70" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "degreePrograms70Type", propOrder = {
    "degreeProgram70"
})
public class DegreePrograms70Type {

    protected List<DegreeProgramDto70> degreeProgram70;

    /**
     * Gets the value of the degreeProgram70 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the degreeProgram70 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDegreeProgram70().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DegreeProgramDto70 }
     * 
     * 
     */
    public List<DegreeProgramDto70> getDegreeProgram70() {
        if (degreeProgram70 == null) {
            degreeProgram70 = new ArrayList<DegreeProgramDto70>();
        }
        return this.degreeProgram70;
    }

}
