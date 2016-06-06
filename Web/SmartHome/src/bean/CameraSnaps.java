package bean;

import java.sql.Timestamp;

public class CameraSnaps {
	
	private Timestamp datetime;
	private String cameraSnaps;
	
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	public String getCameraSnaps() {
		return cameraSnaps;
	}
	public void setCameraSnaps(String cameraSnaps) {
		this.cameraSnaps = cameraSnaps;
	}
	

}
