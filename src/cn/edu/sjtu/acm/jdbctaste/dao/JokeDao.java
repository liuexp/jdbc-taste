package cn.edu.sjtu.acm.jdbctaste.dao;

import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.entity.Joke;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public interface JokeDao {

	public int insertJoke(Joke joke);

	public boolean deleteJoke(Joke joke);

	public boolean updateJoke(Joke joke);

	/**
	 * Find all the jokes that person ever told
	 * @param person
	 * @return A list contains all the jokes this person ever told
	 */
	public List<Joke> findJokesOfPerson(Person person);
	
	/**
	 * Get all jokes in the database
	 * @return A list contains all jokes
	 */
	public List<Joke> getAllJokes();
}
