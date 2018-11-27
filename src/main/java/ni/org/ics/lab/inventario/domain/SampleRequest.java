package ni.org.ics.lab.inventario.domain;

import ni.org.ics.lab.inventario.domain.audit.Auditable;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ics
 */

@Entity
@Table(name = "inv_solicitud_mx", catalog = "inventario")
public class SampleRequest extends BaseMetaData implements Auditable {

    private String idRequest;
    private Date requestDate;
    private String respRequest;
    private String authorizedBy;


    @Id
    @Column(name = "ID_SOLICITUD", nullable = false, length = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }


    @Column(name = "FECHA_SOLICITUD", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Column(name = "RESPONSABLE_SOLICITUD", nullable = false, length =100)
    public String getRespRequest() {
        return respRequest;
    }

    public void setRespRequest(String respRequest) {
        this.respRequest = respRequest;
    }

    @Column(name = "AUTORIZADO", nullable = false, length =100)
    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }


    @Override
    public boolean equals(Object other) {

        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SampleRequest))
            return false;

        SampleRequest castOther = (SampleRequest) other;

        return (this.getIdRequest()).equals(castOther.getIdRequest());
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        //Campos no auditables en la tabla
        if(fieldname.matches("recordDate")||fieldname.matches("recordUser")){
            return false;
        }
        return true;
    }


}
