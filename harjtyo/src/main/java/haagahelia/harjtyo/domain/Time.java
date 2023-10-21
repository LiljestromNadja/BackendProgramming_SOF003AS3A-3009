package haagahelia.harjtyo.domain;

import java.time.LocalDateTime;

public class Time {
	
	private String clock; 
	
	private String currentdate;

	public Time() {
		
	}
	
	public Time(String clock, String currentdate) {
		super();
		this.clock = clock;
		this.currentdate = currentdate;
	}

	public String getClock() {
		// kellonaika
		int min = LocalDateTime.now().getMinute();
		int h = LocalDateTime.now().getHour();
		int secs = LocalDateTime.now().getSecond();
	
		String sMin;
		String sH;
		String sSecs;
	
		if (min < 10) {
			sMin = "0" + LocalDateTime.now().getMinute();
		} else {
			sMin = LocalDateTime.now().getMinute() + "";
		}
	
		if (h < 10) {
			sH = "0" + LocalDateTime.now().getHour();
		} else {
			sH = LocalDateTime.now().getHour() + "";
		}
		if (secs < 10) {
			sSecs = "0" + LocalDateTime.now().getSecond();
		} else {
			sSecs = LocalDateTime.now().getSecond() + "";
		}
	
		String sldtTime = sH + ":" + sMin + ":" + sSecs;
		
		clock = sldtTime;
		return clock;
	}

	public void setClock(String clock) {
		
		this.clock = clock;
	}

	public String getCurrentdate() {
		
		// pvm
		String yyyyString = LocalDateTime.now().getYear() + "-";
		int dd = LocalDateTime.now().getDayOfMonth();
		int mm = LocalDateTime.now().getMonthValue();
		String ddString;
		String mmString;

		if (dd < 10) {
			ddString = "0" + LocalDateTime.now().getDayOfMonth() + "";
		} else {
			ddString = LocalDateTime.now().getDayOfMonth() + "";
		}

		if (mm < 10) {
			mmString = "0" + LocalDateTime.now().getMonthValue() + "-";
		} else {
			mmString = LocalDateTime.now().getMonthValue() + "-";
		}
		String sldtDate = yyyyString + mmString + ddString;
		
		currentdate = sldtDate;
		return currentdate;
	}

	public void setCurrentdate(String currentdate) {
		
		this.currentdate = currentdate;
	}

	@Override
	public String toString() {
		return "Time [clock=" + clock + ", currentdate=" + currentdate + "]";
	}
	
	
	
	
	
	
	

}
