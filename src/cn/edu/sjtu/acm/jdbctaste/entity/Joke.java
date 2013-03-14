package cn.edu.sjtu.acm.jdbctaste.entity;

public class Joke {
	
	private Person speaker;
	
	private String body;
	
	private java.sql.Timestamp postTime;
	
	public Person getSpeaker () {
		return speaker;
	}
	
	public String getBody () {
		return body;
	}
	
	public java.sql.Timestamp getPostTime () {
		return postTime;
	}
	
}
