package cn.edu.sjtu.acm.jdbctaste.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.dao.CommentDao;
import cn.edu.sjtu.acm.jdbctaste.entity.Comment;
import cn.edu.sjtu.acm.jdbctaste.entity.Joke;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public class SqliteCommentDao implements CommentDao {

	private final Connection conn;
	
	public static final int IDX_ID = 1, IDX_JOKE = 2, IDX_COMMENTATOR = 3, IDX_BODY = 4, IDX_POST_TIME = 5;
	
	public SqliteCommentDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insertComment(Comment comment) {

		int ret = -1;

		try {
			PreparedStatement stmt = conn.prepareStatement(
					"insert into comment (jokeid, commentator, body) values (?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, comment.getJoke().getId());
			stmt.setInt(2, comment.getCommentator().getId());
			stmt.setString(2, comment.getBody());

			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				int id = rs.getInt(1);
				comment.setId(id);
				ret = id;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ret = -1;
		}

		return ret;
	}

	@Override
	public boolean deleteComment(Comment comment) {
		boolean flag ;
		try {
			PreparedStatement stmt = conn.prepareStatement(
					"delete from comment where id = ?;",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, comment.getId());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (!rs.next()) {
				//FIXME: what if delete non existing stuff
			}
			flag = true;
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateComment(Comment comment) {
		boolean flag;
		try {
			PreparedStatement stmt = conn.prepareStatement(
					"update comment set jokeid=?, commentator=?, body=? where id=? ;",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, comment.getJoke().getId());
			stmt.setInt(2, comment.getCommentator().getId());
			stmt.setString(3, comment.getBody());
			stmt.setInt(4, comment.getId());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (!rs.next()) {
				//FIXME: what if delete non existing stuff
			}
			flag = true;
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Comment> findCommentsOfPerson(Person person) {
		List<Comment> ret = new LinkedList<Comment>();
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from comment where commentator=? ;");
			stmt.setInt(1, person.getId());

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				ret.add(new Comment(rs.getInt(IDX_ID),
							SqliteDaoFactory.getInstance().getJokeDao().findJokeById(rs.getInt(IDX_JOKE)),
							SqliteDaoFactory.getInstance().getPersonDao().findPersonById(person.getId()),
							rs.getString(IDX_BODY),
							rs.getTimestamp(IDX_POST_TIME)));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ret;
	}

	@Override
	public List<Comment> findCommentsReceived(Person person) {
		List<Comment> ret = new LinkedList<Comment>();
		List<Joke> tmp = SqliteDaoFactory.getInstance().getJokeDao().findJokesOfPerson(person);
		for(Joke x : tmp){
			ret.addAll(findCommentsOfJoke(x));
		}
		return ret;
	}

	@Override
	public List<Comment> findCommentsOfJoke(Joke joke) {
		List<Comment> ret = new LinkedList<Comment>();
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from comment where jokeid=? ;");
			stmt.setInt(1, joke.getId());

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				ret.add(new Comment(rs.getInt(IDX_ID),
						SqliteDaoFactory.getInstance().getJokeDao().findJokeById(joke.getId()),
							SqliteDaoFactory.getInstance().getPersonDao().findPersonById(rs.getInt(IDX_COMMENTATOR)),
							rs.getString(IDX_BODY),
							rs.getTimestamp(IDX_POST_TIME)));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ret;
	}

	@Override
	public List<Comment> getAllComments() {
		List<Comment> ret = new LinkedList<Comment>();

		Statement stmt;
		try {
			stmt = conn.createStatement();

			stmt.execute("select * from comment;");
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				ret.add(new Comment(rs.getInt(IDX_ID),
							SqliteDaoFactory.getInstance().getJokeDao().findJokeById(rs.getInt(IDX_JOKE)),
							SqliteDaoFactory.getInstance().getPersonDao().findPersonById(rs.getInt(IDX_COMMENTATOR)),
							rs.getString(IDX_BODY),
							rs.getTimestamp(IDX_POST_TIME)));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ret;
	}

	@Override
	public Comment findCommentById(int id) {
		Comment ret = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from comment where id = ?;");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			ret = new Comment(rs.getInt(IDX_ID),
							SqliteDaoFactory.getInstance().getJokeDao().findJokeById(rs.getInt(IDX_JOKE)),
							SqliteDaoFactory.getInstance().getPersonDao().findPersonById(rs.getInt(IDX_COMMENTATOR)),
							rs.getString(IDX_BODY),
							rs.getTimestamp(IDX_POST_TIME));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

}
