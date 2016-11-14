/**
 * 
 */
package hu.uszeged.inf.onlalg.scheduling.data;

import java.util.Set;

/**
 * @author Attila
 *
 */
public class Job {
	private String id;
	private long duration;
	private long rejectionPrice;
	private Set<String> conflictedJobIds;
	private transient Set<Job> conflictedJobs;

	/**
	 * @param id
	 * @param duration
	 * @param rejectionPrice
	 * @param conflictedJobIds
	 */
	public Job(String id, int duration, int rejectionPrice, Set<String> conflictedJobIds) {
		super();
		this.id = id;
		this.duration = duration;
		this.rejectionPrice = rejectionPrice;
		this.conflictedJobIds = conflictedJobIds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Job [id=" + id + ", duration=" + duration + ", rejectionPrice=" + rejectionPrice + ", conflictedJobIds="
				+ conflictedJobIds + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @return the rejectionPrice
	 */
	public long getRejectionPrice() {
		return rejectionPrice;
	}

	/**
	 * @return the conflictedJobIds
	 */
	public Set<String> getConflictedJobIds() {
		return conflictedJobIds;
	}

	/**
	 * @return the conflictedJobs
	 */
	public Set<Job> getConflictedJobs() {
		return conflictedJobs;
	}

	/**
	 * @param conflictedJobs
	 *            the conflictedJobs to set
	 */
	public void setConflictedJobs(Set<Job> conflictedJobs) {
		this.conflictedJobs = conflictedJobs;
	}
}
