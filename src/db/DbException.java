package db;

public class DbException extends RuntimeException {

	private long serialVersionUID = 1l;
	
	public DbException(String msg) {
		super(msg);
	}
}
