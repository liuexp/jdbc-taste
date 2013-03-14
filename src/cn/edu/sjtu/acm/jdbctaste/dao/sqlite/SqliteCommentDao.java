package cn.edu.sjtu.acm.jdbctaste.dao.sqlite;

import java.sql.Connection;
import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.dao.CommentDao;
import cn.edu.sjtu.acm.jdbctaste.entity.Comment;
import cn.edu.sjtu.acm.jdbctaste.entity.Joke;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public class SqliteCommentDao implements CommentDao {

	private final Connection conn;
	
	public static final int IDX_ID = 1, IDX_BODY = 2, IDX_JOKE = 3, IDX_COMMENTATOR = 4, IDX_POST_TIME = 5;
	
	public SqliteCommentDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insertComment(Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Comment> findCommentsOfPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findCommentsReceived(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findCommentsOfJoke(Joke joke) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getAllComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment findCommentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
