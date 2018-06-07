# CPU Schedulers Simulator
A java program to simulate a multi-level queue scheduler, such that each queue should implement one of these algorithms:
1.	First Come First Served (FCFS)
2.	Non preemptive Shortest- Job  First (SJF) Scheduling
3.	Round Robin (RR)

The processes of each queue will run based on the priority of the queue. Such that, the processes of the queue with highest priority will run first then the processes of the queue with low priority.

## Program Input 
1.	No of queues
For each queue you need to receive the following parameters from the user:
  *	Priority (which may be 1, 2, or 3) 
  *	E.g., 1 for min priority, 2 for mid priority, or 3 for max priority. Such that, the queues with the same priority can run sequential.
2.	Algorithm
  *	E.g., FCFS, JSF, or RR. Such that, take from the user the required quantum in case of RR.
3.	Number of processes 
For Each Process you need to receive the following parameters from the user:
*	Process Name
*	Process Arrival Time 
*	Process Burst Time

## Program Output
For each scheduler output the following:
1.	Processes execution order 
2.	Turnaround time for each process	
3.	Average Turnaround Time


