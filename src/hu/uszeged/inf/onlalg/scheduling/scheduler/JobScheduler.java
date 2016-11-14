/**
 * 
 */
package hu.uszeged.inf.onlalg.scheduling.scheduler;

import java.util.List;

import hu.uszeged.inf.onlalg.scheduling.data.Job;
import hu.uszeged.inf.onlalg.scheduling.data.Machine;

/**
 * @author Attila
 *
 */
public abstract class JobScheduler {
	protected List<Machine> machines;

	/**
	 * @param machines
	 */
	public JobScheduler(List<Machine> machines) {
		super();
		this.machines = machines;
	}

	/**
	 * @param job
	 * @return
	 */
	public abstract Machine scheduleJob(Job job);
}
