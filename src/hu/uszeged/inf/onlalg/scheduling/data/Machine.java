/**
 * 
 */
package hu.uszeged.inf.onlalg.scheduling.data;

/**
 * @author Attila
 *
 */
public class Machine {
	private String id;

	/**
	 * @param id
	 */
	public Machine(String id) {
		super();
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Machine [id=" + id + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
}
