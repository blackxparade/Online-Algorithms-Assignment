/**
 * 
 */
package hu.uszeged.inf.onlalg.scheduling.data;

import java.util.List;

/**
 * @author Attila
 *
 */
public class SchedulingInput {
	private List<Machine> machines;
	private List<Job> jobs;

	/**
	 * @param machines
	 * @param jobs
	 */
	public SchedulingInput(List<Machine> machines, List<Job> jobs) {
		super();
		this.machines = machines;
		this.jobs = jobs;
	}

	/**
	 * @return the machines
	 */
	public List<Machine> getMachines() {
		return machines;
	}

	/**
	 * @return the jobs
	 */
	public List<Job> getJobs() {
		return jobs;
	}
}
