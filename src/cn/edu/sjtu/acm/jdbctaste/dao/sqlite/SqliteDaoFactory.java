package cn.edu.sjtu.acm.jdbctaste.dao.sqlite;

import cn.edu.sjtu.acm.jdbctaste.dao.CommentDao;
import cn.edu.sjtu.acm.jdbctaste.dao.DaoFactory;
import cn.edu.sjtu.acm.jdbctaste.dao.JokeDao;
import cn.edu.sjtu.acm.jdbctaste.dao.PersonDao;

public class SqliteDaoFactory extends DaoFactory {

	private static SqliteDaoFactory INSTANCE;
	
	public static DaoFactory getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SqliteDaoFactory();
		return INSTANCE;
	}

	private SqliteDaoFactory (){
	}
	
	@Override
	public PersonDao getPersonDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JokeDao getJokeDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDao getCommentDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
