package cn.edu.sjtu.acm.jdbctaste.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.dao.PersonDao;
import cn.edu.sjtu.acm.jdbctaste.entity.Person;

public class SqlitePersonDao implements PersonDao {

	public static final int IDX_ID = 1, IDX_NAME = 2, IDX_EMAIL = 3;

	private final Connection conn;

	public SqlitePersonDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insertPerson(Person person) {

		int ret = -1;

		try {
			PreparedStatement stmt = conn.prepareStatement(
					"insert into person (name, email) values (?,?);",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, person.getName());
			stmt.setString(2, person.getEmail());

			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				int id = rs.getInt(1);
				person.setId(id);
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
	public boolean deletePerson(Person person) {
		boolean flag ;
		try {
			PreparedStatement stmt = conn.prepareStatement(
					"delete from person where id = ?;",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, person.getId());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
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
	public boolean updatePerson(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person findPersonByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOfJokes(Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Person> getAllPerson() {
		List<Person> ret = new LinkedList<Person>();

		Statement stmt;
		try {
			stmt = conn.createStatement();

			stmt.execute("select * from person;");
			ResultSet result = stmt.getResultSet();

			while (result.next()) {
				ret.add(new Person(result.getInt(IDX_ID), result
						.getString(IDX_NAME), result.getString(IDX_EMAIL)));
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ret;
	}

	@Override
	public Person findPersonById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
