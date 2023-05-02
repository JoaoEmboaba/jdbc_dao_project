package db;

public class DbIntegrityException extends RuntimeException {

	private long serialVersionUID = 1l;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
}
