/**
 * 
 */
package hu.uszeged.inf.onlalg.scheduling.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hu.uszeged.inf.onlalg.scheduling.data.Job;
import hu.uszeged.inf.onlalg.scheduling.data.Machine;

/**
 * @author Attila
 *
 */
public class JobSchedulerImpl extends JobScheduler {
	long B;
	int m;
	float alpha;
	long[] machineLoad;
	List<Pair> history;
	public JobSchedulerImpl(List<Machine> machines) {
		super(machines);
		B = 0;
		m = machines.size();
		alpha = 0.54f;
		machineLoad = new long[machines.size()];
		history = new ArrayList<>();
	}
	class Pair{
		Machine m;
		Job j;
		
		Pair(Job job, Machine mach){
			j = job;
			m = mach;
		}	
	}

	@Override
	public Machine scheduleJob(Job job) {
		// TODO: Implement Your scheduling algorithm here.
		// RTP_alpha algorithm
		
		// check if the job is in or not in P_0
		// if job is in P_0, reject
		// System.out.println(m);
		if (job.getRejectionPrice() <= (job.getDuration() / (float)m)) return null; 
		
		if (B + job.getRejectionPrice() <= alpha * job.getDuration()){
			B += job.getRejectionPrice();
			return null;
		}
		
		long min = Long.MAX_VALUE;
		int min_Id = 0;
		for (int i=0; i<machines.size(); i++) {
				
					if (machineLoad[i] < min && !checkConflict(job,machines.get(i))) {
						min = machineLoad[i];
						min_Id = i;
					}
		}
		
		if(min == Long.MAX_VALUE){
			B += job.getRejectionPrice();
			return null;
		}

		// add the actual Job's duration to the chosen machine's load 
		machineLoad[min_Id] += job.getDuration();
		history.add(new Pair(job, machines.get(min_Id)));

		return machines.get(min_Id);
	}
	
	public boolean checkConflict(Job job, Machine m){
		
			for(int i=0; i<history.size(); i++){
				if(job.getConflictedJobIds().contains(history.get(i).j.getId()) 
				   && history.get(i).m.getId() == m.getId())
					  return true;
			}		
		return false;
	}
	
	
	
	

}


