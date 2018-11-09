package ni.org.ics.lab.inventario.domain;

import ni.org.ics.lab.inventario.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ics
 */

@Entity
@Table(name = "inv_solicitud_mx_detalle", catalog = "inventario")
public class SampleRequestDetail extends BaseMetaData implements Auditable {

    private String idDetail;
    private SampleRequest idRequest;
    private String code;
    private String aliCode;
    private float aliVol;
    private float subAliVol;
    private String study;
    private String alicTypeName;
    private Date samplingDate;
    private String purposeRequest;
    private String destination;
    private String comments;


    @Id
    @Column(name = "ID_DETAIL", nullable = false, length = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="ID_SOLICITUD")
    @ForeignKey(name = "REQUEST_FK")
    public SampleRequest getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(SampleRequest idRequest) {
        this.idRequest = idRequest;
    }

    @Column(name = "CODIGO", nullable = false, length =50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "CODIGO_ALIC", nullable = false, length =50)
    public String getAliCode() {
        return aliCode;
    }

    public void setAliCode(String aliCode) {
        this.aliCode = aliCode;
    }

    @Column(name = "VOLUMEN")
    public float getAliVol() {
        return aliVol;
    }

    public void setAliVol(float aliVol) {
        this.aliVol = aliVol;
    }

    @Column(name = "VOLUMEN_REQUERIDO")
    public float getSubAliVol() {
        return subAliVol;
    }

    public void setSubAliVol(float subAliVol) {
        this.subAliVol = subAliVol;
    }

    @Column(name = "NOMBRE_ESTUDIO", nullable = false, length =150)
    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }


    @Column(name = "TIPO_ALIC", length =50)
    public String getAlicTypeName() {
        return alicTypeName;
    }

    public void setAlicTypeName(String alicTypeName) {
        this.alicTypeName = alicTypeName;
    }

    @Column(name = "FECHA_TOMAMX", nullable = false)
    public Date getSamplingDate() {
        return samplingDate;
    }

    public void setSamplingDate(Date samplingDate) {
        this.samplingDate = samplingDate;
    }
    @Column(name = "PROPOSITO", nullable = false)
    public String getPurposeRequest() {
        return purposeRequest;
    }

    public void setPurposeRequest(String purposeRequest) {
        this.purposeRequest = purposeRequest;
    }

    @Column(name = "DESTINO", nullable = false, length =200)
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column(name = "OBSERVACIONES", length =200)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object other) {

        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SampleRequestDetail))
            return false;

        SampleRequestDetail castOther = (SampleRequestDetail) other;

        return (this.getIdDetail()).equals(castOther.getIdDetail());
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
