package main;
import java.util.ArrayList;
import java.util.List;

public class PC {
	private String id;
	private String ip;
	private String so;
	private List<Puerto> puertos = new ArrayList<>();
	
	public PC(String id, String ip, String so) {
		
		this.id = id;
		this.ip = ip;
		this.so = so;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	public List<Puerto> getPuertos() {
		return puertos;
	}

	public void setPuertos(List<Puerto> puertos) {
		this.puertos = puertos;
	}
	
	 public void agregarPuerto(Puerto p) {
	        this.puertos.add(p);
	    }
	 
	 

	
	
	
	
	

}
