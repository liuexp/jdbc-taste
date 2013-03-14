package cn.edu.sjtu.acm.jdbctaste.task.test;

import cn.edu.sjtu.acm.jdbctaste.Taste;
import cn.edu.sjtu.acm.jdbctaste.dao.DaoFactory;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

/**
 * This test case shows how to insert a person and get it back
 * @author furtherlee
 *
 */
public class NaiveTaskA extends TestTask {

	private DaoFactory factory;
	
	public NaiveTaskA (Taste taste) {
		this.factory = taste.getDaoFactory();
	}
	
	@Override
	public boolean doit() {
		Person godel = new Person("Kurt Godel", "godel@god.org");
		
		factory.getPersonDao().insertPerson(godel);
		
		Person fetchedPerson = factory.getPersonDao().getAllPerson().get(0);
		try {
			assertEqual(fetchedPerson.getName(), "Kurt Godel");
			assertEqual(fetchedPerson.getEmail(), "godel@god.org");
			assertEqual(fetchedPerson.getId(), godel.getId());
		} catch (RuntimeException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
