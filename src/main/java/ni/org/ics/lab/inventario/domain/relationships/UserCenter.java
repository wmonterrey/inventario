package ni.org.ics.lab.inventario.domain.relationships;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import ni.org.ics.lab.inventario.domain.BaseMetaData;
import ni.org.ics.lab.inventario.domain.Center;
import ni.org.ics.lab.inventario.domain.audit.Auditable;
import ni.org.ics.lab.inventario.users.model.UserSistema;


import org.hibernate.annotations.ForeignKey;

/**
 * Simple objeto de dominio que representa un centro para un usuario
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_usuarios_centros", catalog = "inventario")
public class UserCenter extends BaseMetaData implements Auditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserCenterId userCenterId;
	private UserSistema user;
	private Center centro;
	
	public UserCenter() {
	}
	
	public UserCenter(UserCenterId userCenterId, Date recordDate, String recordUser) {
		super(recordDate, recordUser);
		this.userCenterId = userCenterId;
	}
	
	public UserCenter(UserCenterId userCenterId, UserSistema user, Center centro
			, String recordUser, Date recordDate) {
		super(recordDate, recordUser);
		this.userCenterId = userCenterId;
		this.user = user;
		this.centro = centro;
	}
	
	@Id
	public UserCenterId getUserCenterId() {
		return userCenterId;
	}
	public void setUserCenterId(UserCenterId userCenterId) {
		this.userCenterId = userCenterId;
	}
	
	@ManyToOne
	@JoinColumn(name="NOMBRE_USUARIO", insertable = false, updatable = false)
	@ForeignKey(name = "UC_USUARIOS_FK")
	public UserSistema getUser() {
		return user;
	}
	
	public void setUser(UserSistema user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="CENTRO", insertable = false, updatable = false)
	@ForeignKey(name = "UC_CENTROS_FK")
	public Center getCentro() {
		return centro;
	}
	public void setCentro(Center centro) {
		this.centro = centro;
	}
	
	@Override
	public String toString(){
		return centro.getCenterName();
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
