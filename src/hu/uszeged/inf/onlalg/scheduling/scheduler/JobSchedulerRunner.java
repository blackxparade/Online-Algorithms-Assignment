/**
 * 
 */
package hu.uszeged.inf.onlalg.scheduling.scheduler;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.gson.Gson;

import hu.uszeged.inf.onlalg.scheduling.data.Job;
import hu.uszeged.inf.onlalg.scheduling.data.Machine;
import hu.uszeged.inf.onlalg.scheduling.data.SchedulingInput;

/**
 * @author Attila
 *
 */
public class JobSchedulerRunner {
	private SchedulingInput schedulingInput;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JobSchedulerRunner runner = new JobSchedulerRunner("input.json");
			long objValue = runner.runScheduler(new JobSchedulerImpl(runner.getSchedulingInput().getMachines()));
			System.out.println("Objective value = " + objValue);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public JobSchedulerRunner(String filePath) throws IOException {
		super();
		loadSchedulingInput(filePath);

	}

	private void loadSchedulingInput(String filePath) throws IOException {
		try (FileReader fileReader = new FileReader(filePath)) {
			schedulingInput = new Gson().fromJson(fileReader, SchedulingInput.class);

			// test id duplicates
			long distinctCountJobs = schedulingInput.getJobs().stream().map(job -> job.getId()).distinct().count();
			if (schedulingInput.getJobs().size() > distinctCountJobs) {
				throw new RuntimeException("Job ids are not distinct!");
			}
			long distinctCountMachines = schedulingInput.getMachines().stream().map(machine -> machine.getId())
					.distinct().count();
			if (schedulingInput.getMachines().size() > distinctCountMachines) {
				throw new RuntimeException("Machine ids are not distinct!");
			}

			// test if conflicts are online
			for (int j = 0; j < schedulingInput.getJobs().size(); ++j) {
				Job job = schedulingInput.getJobs().get(j);
				if (IntStream.range(j, schedulingInput.getJobs().size()).anyMatch(
						j2 -> job.getConflictedJobIds().contains(schedulingInput.getJobs().get(j2).getId()))) {
					throw new RuntimeException("Conflicts are not valid!");
				}
			}

			// set the conflictedJob objects
			Map<String, Job> jobsById = schedulingInput.getJobs().stream()
					.collect(Collectors.toMap(Job::getId, Function.identity()));
			schedulingInput.getJobs().stream().forEach(job -> job.setConflictedJobs(
					job.getConflictedJobIds().stream().map(jobId -> jobsById.get(jobId)).collect(Collectors.toSet())));
		} catch (IOException e) {
			throw e;
		}
	}

	private long runScheduler(JobScheduler jobScheduler) {
		long sumRejectionPrice = 0;
		Map<Machine, Set<Job>> machineScheduledJobs = schedulingInput.getMachines().stream()
				.collect(Collectors.toMap(Function.identity(), m -> new HashSet<>()));

		for (Job job : schedulingInput.getJobs()) {
			Machine scheduledMachine = jobScheduler.scheduleJob(job);
			if (scheduledMachine == null) {
				sumRejectionPrice += job.getRejectionPrice();
				System.out.println(job + " --> REJECTED!");
			} else {
				if (!schedulingInput.getMachines().contains(scheduledMachine)) {
					throw new RuntimeException("Machine does not exist!");
				} else if (machineScheduledJobs.get(scheduledMachine).stream()
						.anyMatch(machineJob -> job.getConflictedJobs().contains(machineJob))) {
					throw new RuntimeException(
							"Job-conflict violated for " + job + "by scheduling to " + scheduledMachine);
				} else {
					machineScheduledJobs.get(scheduledMachine).add(job);
					System.out.println(job + " --> Scheduled to: " + scheduledMachine);
				}
			}
		}

		long makespan = machineScheduledJobs.entrySet().stream()
				.mapToLong(entry -> entry.getValue().stream().mapToLong(job -> job.getDuration()).sum()).max()
				.orElse(0);

		return sumRejectionPrice + makespan;
	}

	/**
	 * @return the schedulingInput
	 */
	public SchedulingInput getSchedulingInput() {
		return schedulingInput;
	}
}
