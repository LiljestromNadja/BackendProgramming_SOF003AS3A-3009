package hh.bookstore17.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Answer {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //mariadb
	private long answerid;
	
	//@NotEmpty(message = "Your message cannot be empty!")
	private String answermessage;
	
	@ManyToOne
	@JoinColumn(name = "applicationuserid")
	//@JsonIgnore //Lisätty 6.11
	private ApplicationUser applicationuser; //tämän pitää olla sama ApplicationUser.javassa MappedBy

	private String answercreated;
	
	@ManyToOne
	@JoinColumn(name="commentid")
	private Comment comment; //Tämän pitää olla sama kuin Comment.javassa MappedBy

	public Answer() {
		//super();
	}

	public Answer(String answermessage,	ApplicationUser applicationuser, String answercreated, Comment comment) {
		super();
		
		this.answermessage = answermessage;
		this.applicationuser = applicationuser;
		this.answercreated = answercreated;
		this.comment = comment;
	}

	public long getAnswerid() {
		return answerid;
	}

	public void setAnswerid(long answerid) {
		this.answerid = answerid;
	}

	public String getAnswermessage() {
		return answermessage;
	}

	public void setAnswermessage(String answermessage) {
		this.answermessage = answermessage;
		
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
		//System.out.println(sldtTime);

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
		//System.out.println(sldtDate);
		
		String timeAnswered = sldtDate + " " + sldtTime;
		setAnswercreated(timeAnswered);
	}

	public ApplicationUser getApplicationuser() {
		return applicationuser;
	}

	public void setApplicationuser(ApplicationUser applicationuser) {
		this.applicationuser = applicationuser;
	}

	public String getAnswercreated() {
		return answercreated;
	}

	public void setAnswercreated(String answercreated) {
		this.answercreated = answercreated;
	}


	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Answer [answerid=" + answerid + ", answermessage=" + answermessage + ", applicationuser="
				+ applicationuser + ", answercreated=" + answercreated + "]";
	}
	
	
	
	
	
	
	
}
