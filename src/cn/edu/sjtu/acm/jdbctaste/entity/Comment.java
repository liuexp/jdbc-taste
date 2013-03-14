package cn.edu.sjtu.acm.jdbctaste.entity;

public class Comment {

	private Joke joke;

	private Person commentater;

	private String body;

	private java.sql.Timestamp postTime;

	public Joke getJoke() {
		return joke;
	}

	public Person getCommentator() {
		return commentater;
	}

	public String getBody() {
		return body;
	}

	public java.sql.Timestamp getPostTime() {
		return postTime;
	}
}
