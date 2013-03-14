package cn.edu.sjtu.acm.jdbctaste.dao;

import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.entity.Comment;
import cn.edu.sjtu.acm.jdbctaste.entity.Joke;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public interface CommentDao {

	public int insertComment(Comment comment);

	public boolean deleteComment(Comment comment);

	public boolean updateComment(Comment comment);
	
	/**
	 * Get all the comments this person gave to others
	 * @param person
	 * @return A list contains all the comments this person gave to others
	 */
	public List<Comment> findCommentsOfPerson(Person person);

	/**
	 * Get all the comments this person received from others
	 * @param person
	 * @return A list contains all the comments this person received from others
	 */
	public List<Comment> findCommentsReceived(Person person);
	
	/**
	 * Get all the comments of a specific joke
	 * @param joke
	 * @return A list contains all the comments of a specific joke
	 */
	public List<Comment> findCommentsOfJoke(Joke joke);

	/**
	 * Get all comments
	 * @return A list contains all comments
	 */
	public List<Comment> getAllComments();

}
