package cn.edu.sjtu.acm.jdbctaste.dao;

import cn.edu.sjtu.acm.jdbctaste.dao.sqlite.SqliteDaoFactory;

public abstract class DaoFactory {

	public static final int SQLITE = 0;

	public abstract PersonDao getPersonDao();

	public abstract JokeDao getJokeDao();

	public abstract CommentDao getCommentDao();

	public static DaoFactory getDaoFactory(int type) {
		switch (type) {
		case SQLITE:
			return SqliteDaoFactory.getInstance();
		default:
			return null;
		}
	}
}
