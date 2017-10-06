package ni.org.ics.lab.inventario.domain.audit;

public interface Auditable {
	
	public boolean isFieldAuditable(String fieldname);

}
