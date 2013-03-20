package cn.edu.sjtu.acm.jdbctaste.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.dao.JokeDao;
import cn.edu.sjtu.acm.jdbctaste.dao.PersonDao;
import cn.edu.sjtu.acm.jdbctaste.entity.Joke;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public class SqliteJokeDao implements JokeDao {

	public static final int IDX_ID = 1, IDX_BODY = 2, IDX_SPEAKER = 3,
			IDX_POST_TIME = 4, IDX_ZAN = 5;

	private final Connection conn;

	public SqliteJokeDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insertJoke(Joke joke) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteJoke(Joke joke) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateJoke(Joke joke) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Joke> findJokesOfPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Joke> getAllJokes() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
}
