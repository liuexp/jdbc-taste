package cn.edu.sjtu.acm.jdbctaste.task;

import cn.edu.sjtu.acm.jdbctaste.Taste;
import cn.edu.sjtu.acm.jdbctaste.TasteTask;

/**
 * This task is to create tree tables in sqlite, called person, joke and comment
 * Caution: Don't use reference, we first use naive way to do the same thing.
 * Notice: In sqlite, database used is infereced from your connection url, so no "create database taste;" or "use taste"
 * @author furtherlee
 */
public class CreateTablesTask implements TasteTask {

	private Taste taste;

	public CreateTablesTask(Taste taste) {
		this.taste = taste;
	}

	private static final String PERSON_SCHEMA = 
			"create table person(" +
			"id int auto_increment," +
			"name varchar(200) not null," +
			"email varchar(200) not null," +
			"primary key(id));";

	// TODO Write a schema for table joke
	private static final String JOKE_SCHEMA = "";

	// TODO Write a schema for table comment
	private static final String COMMENT_SCHEMA = "";

	@Override
	public boolean doit() {
		try {
			taste.getDaoFactory().getConn().createStatement().execute(PERSON_SCHEMA);
			// TODO create joke table and comment table
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
