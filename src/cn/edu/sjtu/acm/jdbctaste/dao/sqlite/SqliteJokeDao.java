package cn.edu.sjtu.acm.jdbctaste.dao.sqlite;

import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.dao.JokeDao;
import cn.edu.sjtu.acm.jdbctaste.entity.Joke;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public class SqliteJokeDao implements JokeDao {

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

}
