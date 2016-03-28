package nl.first8.kiteclub.api;

public class SessionInfo {
	private String contextPath;
	private String username;
	private String nickname;
	private String accountUrl;

	public SessionInfo() {
		// For JAX-B
	}

	public SessionInfo(String contextPath, String username, String nickname, String accountUrl) {
		this.contextPath = contextPath;
		this.username = username;
		this.nickname = nickname;
		this.accountUrl = accountUrl;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAccountUrl() {
		return accountUrl;
	}

	public void setAccountUrl(String accountUrl) {
		this.accountUrl = accountUrl;
	}
}
