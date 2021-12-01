package model.Bean;

import java.util.Date;
import java.text.SimpleDateFormat;

public class History {
	public String id;
	public String userId;
	public String fileName;
	public String status;
	public Date time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(time);
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
