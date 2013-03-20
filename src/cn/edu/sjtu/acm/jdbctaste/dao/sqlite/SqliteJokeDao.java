package cn.edu.sjtu.acm.jdbctaste.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.dao.JokeDao;
import cn.edu.sjtu.acm.jdbctaste.dao.PersonDao;
import cn.edu.sjtu.acm.jdbctaste.entity.Comment;
import cn.edu.sjtu.acm.jdbctaste.entity.Joke;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public class SqliteJokeDao implements JokeDao {

	public static final int IDX_ID = 1, IDX_SPEAKER = 2, IDX_BODY = 3,
			IDX_POST_TIME = 4, IDX_ZAN = 5;

	private final Connection conn;

	public SqliteJokeDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insertJoke(Joke joke) {
		int ret = -1;

		try {
			PreparedStatement stmt = conn.prepareStatement(
					"insert into joke (speaker, body, zan) values (?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, joke.getSpeaker().getId());
			stmt.setString(2, joke.getBody());
			stmt.setInt(3, joke.getZan());

			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				int id = rs.getInt(1);
				joke.setId(id);
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
	public boolean deleteJoke(Joke joke) {
		boolean flag ;
		try {
			List<Comment> tmp = SqliteDaoFactory.getInstance().getCommentDao().findCommentsOfJoke(joke);
			for(Comment x:tmp){
				SqliteDaoFactory.getInstance().getCommentDao().deleteComment(x);
			}
			
			PreparedStatement stmt = conn.prepareStatement(
					"delete from joke where id = ?;",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, joke.getId());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			flag = rs.next();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateJoke(Joke joke) {
		boolean flag;
		try {
			PreparedStatement stmt = conn.prepareStatement(
					"update joke set speaker=?, body=?, zan=? where id=? ;",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, joke.getSpeaker().getId());
			stmt.setString(2, joke.getBody());
			stmt.setInt(3, joke.getZan());
			stmt.setInt(4, joke.getId());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			
			flag = rs.next();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Joke> findJokesOfPerson(Person person) {
		List<Joke> ret = new LinkedList<Joke>();
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from joke where speaker=? ;");
			stmt.setInt(1, person.getId());

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				ret.add(new Joke(rs.getInt(IDX_ID), person,
						rs.getString(IDX_BODY),
						rs.getTimestamp(IDX_POST_TIME), rs.getInt(IDX_ZAN)));
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
	public List<Joke> getAllJokes() {
		List<Joke> ret = new LinkedList<Joke>();

		Statement stmt;
		try {
			stmt = conn.createStatement();

			stmt.execute("select * from joke;");
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				PersonDao personDao = SqliteDaoFactory.getInstance().getPersonDao();
				Person speaker = personDao.findPersonById(rs.getInt(IDX_SPEAKER));
				ret.add(new Joke(rs.getInt(IDX_ID), speaker,
						rs.getString(IDX_BODY),
						rs.getTimestamp(IDX_POST_TIME), rs.getInt(IDX_ZAN)));
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
	public Joke findJokeById(int id) {
		Joke ret = null;

		try {
			PreparedStatement stmt = conn
					.prepareStatement("select * from joke where id = ?;");
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if (!result.next())
				return null;

			PersonDao personDao = SqliteDaoFactory.getInstance().getPersonDao();
			Person speaker = personDao.findPersonById(result.getInt(IDX_SPEAKER));
			
			ret = new Joke(result.getInt(IDX_ID), speaker,
					result.getString(IDX_BODY),
					result.getTimestamp(IDX_POST_TIME), result.getInt(IDX_ZAN));
			
			result.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Joke> findJokesWithZanMoreThan(int zan) {
		List<Joke> ret = new LinkedList<Joke>();

		try {
			PreparedStatement stmt = conn.prepareStatement("select * from joke where zan > ?;");
			stmt.setInt(1, zan);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PersonDao personDao = SqliteDaoFactory.getInstance().getPersonDao();
				Person speaker = personDao.findPersonById(rs.getInt(IDX_SPEAKER));
				ret.add(new Joke(rs.getInt(IDX_ID), speaker,
						rs.getString(IDX_BODY),
						rs.getTimestamp(IDX_POST_TIME), rs.getInt(IDX_ZAN)));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ret;
	}
}
