
package de.his.ws.appointmentseriesservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für instructingLecturersType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="instructingLecturersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="instructingLecturer" type="{http://www.his.de/ws/AppointmentSeriesService}InstructingLecturer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instructingLecturersType", propOrder = {
    "instructingLecturer"
})
public class InstructingLecturersType {

    protected List<InstructingLecturer> instructingLecturer;

    /**
     * Gets the value of the instructingLecturer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instructingLecturer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstructingLecturer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstructingLecturer }
     * 
     * 
     */
    public List<InstructingLecturer> getInstructingLecturer() {
        if (instructingLecturer == null) {
            instructingLecturer = new ArrayList<InstructingLecturer>();
        }
        return this.instructingLecturer;
    }

}
