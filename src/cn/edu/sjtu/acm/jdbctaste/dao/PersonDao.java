package cn.edu.sjtu.acm.jdbctaste.dao;

import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public interface PersonDao {
	
	public int insertPerson(Person person);

	public boolean deletePerson(Person person);

	public boolean updatePerson(Person person);

	/**
	 * Try to get a person according to his email
	 * @param email
	 * @return The person with the input email information
	 */
	public Person findPersonByEmail (String email);
	
	/**
	 * Try to get the number of jokes which this person ever told
	 * @param person
	 * @return the number of jokes this person once told
	 */
	public int getNumOfJokes (Person person);
	
	/**
	 * Get All the people
	 * @return A list of person contains all the people
	 */
	public List<Person> getAllPerson ();
}
