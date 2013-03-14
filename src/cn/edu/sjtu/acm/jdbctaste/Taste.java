package cn.edu.sjtu.acm.jdbctaste;

import java.util.ArrayList;
import java.util.List;

import cn.edu.sjtu.acm.jdbctaste.dao.DaoFactory;
import cn.edu.sjtu.acm.jdbctaste.task.CreateTablesTask;
import cn.edu.sjtu.acm.jdbctaste.task.DropTablesTask;
import cn.edu.sjtu.acm.jdbctaste.task.test.NaiveTaskA;

/**
 * A taste for JDBC
 * Welcome to the world of Fatworm!
 * @author furtherlee
 */
public class Taste {

	private List<TasteTask> tasks;

	private DaoFactory factory;

	private int passedTests;
	
	public Taste() {
		tasks = new ArrayList<TasteTask>();
		factory = DaoFactory.getDaoFactory(DaoFactory.SQLITE);
	}

	public DaoFactory getDaoFactory() {
		return factory;
	}

	public void addTask(TasteTask task) {
		tasks.add(task);
	}

	public void doTasks() {
		passedTests = 0;
		for (TasteTask task: tasks)
			if (task.doit()) {
				System.out.println(task.getClass().getSimpleName() + " suceed!");
				passedTests++;
			}
			else
				System.out.println(task.getClass().getSimpleName() + " failed!");
	}

	public void finish() {
		System.out.println("You passed " + passedTests + "/" + tasks.size() + " tests.");
		factory.closeConn();		
	}
	
	public static void main(String[] args) {
		Taste taste = new Taste();
		taste.addTask(new CreateTablesTask(taste));
		taste.addTask(new NaiveTaskA(taste));
		taste.addTask(new DropTablesTask(taste));
		taste.doTasks();
		taste.finish();
	}
}
