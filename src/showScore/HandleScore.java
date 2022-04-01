package showScore;

public class HandleScore {
	private String username;
	String date;
	Integer score;
	String timePlay;
	Integer status;

	public HandleScore(String username, String date, Integer score, String timePlay, Integer status) {
		super();
		this.username = username;
		this.date = date;
		this.score = score;
		this.timePlay = timePlay;
		this.status = status;
	}

	public HandleScore() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getTimePlay() {
		return timePlay;
	}

	public void setTimePlay(String timePlay) {
		this.timePlay = timePlay;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HandleScore [username=" + username + ", date=" + date + ", score=" + score + ", timePlay=" + timePlay
				+ ", status=" + status + "]";
	}

}
