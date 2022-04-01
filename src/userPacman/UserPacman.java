package userPacman;

public class UserPacman {
	private static String username;
	int score;

	public UserPacman(String username, int score) {
		super();
		this.username = username;
		this.score = score;
	}

	public UserPacman(String username) {
		super();
		this.username = username;
	}
	
	public UserPacman(int score) {
		super();
		this.score = score;
	}
	
	public static String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "UserPacman [username=" + username + ", score=" + score + "]";
	}

}
