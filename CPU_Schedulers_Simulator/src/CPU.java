import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class CPU {

	static String Process = "";

	public static Vector<Queue> ReadData() {
		int NoQueues;
		String temp;
		boolean skipped = false;
		Vector<Queue> queues = new Vector<Queue>();

		try {
			Scanner getData = new Scanner(new File("Input.txt"));

			// Read Number of Queues
			NoQueues = Integer.parseInt(getData.nextLine());

			while (getData.hasNextLine()) {
				temp = getData.nextLine();
				if (temp.length() != 0)// NOT EMPTY LINE
				{
					if (skipped == false) {
						// Read queues data and append each queue to vector
						// "queues"
						queues.add((readQueues(NoQueues, temp)));

					} else if (skipped == true) {
						// Read process by process and append them into vector
						// "allProcesses"
						Process p = new Process();
						Queue q = new Queue();

						p = readProcess(temp);
						q = search(p.getQueueName(), queues);
						q.addtovec(p);

					}
				}
				// BLANK LINE
				else {
					skipped = true;
				}
			}
			getData.close();

		} catch (FileNotFoundException /* | InterruptedException */ e) {
			System.out.println("Error while read from \"Input.txt\" file!");
		}
		Collections.sort(queues);
		for (int i = 0; i < queues.size(); i++) {
			Collections.sort(queues.elementAt(i).get_vec());
		}
		return queues;

	}

/*******************************************************************************************************************************/
	public static Queue readQueues(int n, String s) {

		int NoProcesses;
		int QueuePriority;
		int Quantum = 0;
		String QueueName;
		String Algorithm;
		Queue q = new Queue();
		String[] arr = new String[n];
		arr = s.split(";");

		QueueName = arr[0].substring(5);
		QueuePriority = Integer.parseInt((arr[1]).substring(9));
		Algorithm = (arr[2]).substring(10);
		if (Algorithm.equalsIgnoreCase("RR")) {
			if ((arr[3].substring(0, 8)).equalsIgnoreCase("quantum=")) {
				Quantum = Integer.parseInt((arr[3]).substring(8));
				NoProcesses = Integer.parseInt((arr[4]).substring(13));
			} else {
				Quantum = 2;
				NoProcesses = Integer.parseInt((arr[3]).substring(13));
			}

		} else {
			// Quantum = 2; //DEFAULT VALUE
			NoProcesses = Integer.parseInt((arr[3]).substring(13));
		}

		q.setQueueName(QueueName);
		q.set_priority(QueuePriority);
		q.set_algorithm(Algorithm);
		q.set_numofprocess(NoProcesses);

		return q;

	}

/*******************************************************************************************************************************/
public static Process readProcess(String s) {

		int ArrivalTime;
		int BurstTime;
		String QueueName;
		String ProcessName;
		Process p = new Process();
		String[] arr = new String[3]; // WE NEED 4 ELEMENTS IN THE ARRAY TO HOLD
										// PROCESS' DATA

		arr = s.split(";");

		QueueName = arr[0].substring(0);
		ProcessName = (arr[1]).substring(12);
		ArrivalTime = Integer.parseInt((arr[2]).substring(12));
		BurstTime = Integer.parseInt((arr[3]).substring(10));

		p.set_arrivaltime(ArrivalTime);
		p.set_bursttime(BurstTime);
		p.set_name(ProcessName);
		p.setQueueName(QueueName);

		return p;
	}

/*******************************************************************************************************************************/
	public static Queue search(String name, Vector<Queue> v) {
		Queue q = new Queue();
		for (int i = 0; i < v.size(); i++) {
			if ((v.get(i)).getQueueName().equalsIgnoreCase(name)) {
				q = v.get(i);
				break;
			}
		}
		return q;
	}

/*******************************************************************************************************************************/
	public static void printQueue(Vector<Queue> q) {
		Vector<Process> p = new Vector<Process>();
		for (int i = 0; i < q.size(); i++) {
			System.out.println("Queue Name: " + (q.get(i)).getQueueName());
			System.out.println("Queue Priority: " + (q.get(i)).get_priority());
			System.out.println("Queue Algorithm: " + (q.get(i)).get_algorithm());
			System.out.println("Queue Procesess: " + (q.get(i)).get_numofprocess() + "\n");
			// System.out.println("Quantum Value: "+(q.get(i)).getQuantum() +
			// "\n");
			p = ((q.get(i)).get_vec());
			for (int k = 0; k < p.size(); k++) {
				p.get(k).printProcess();
			}
			System.out.println("********************************************************");
		}

	}

	public static Vector<Process> CPU(Vector<Queue> Q) {
		Vector<Process> bigvec = new Vector<Process>();
		int min = 0, time = 0, index = 0, shortestjob = 0;
		boolean check = true, check1 = true;
		
		while (!Q.isEmpty()) {
			check1 = true;
			min = 1000;
			for (int i = 0; i < Q.size(); i++) 
			{
				/******************************
				 * 		FCFS ALGORITHM
				 *****************************/
				if (Q.elementAt(i).get_algorithm().equals("FCFS")) 
				{
					if (Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime() <= time) 
					{
						check1 = false;
						if (min >= time + 1 + Q.elementAt(i).get_vec().elementAt(0).get_bursttime()) 
						{
							if (!check) 
							{
								time++;
							}
							
							check = false;
							Process = Process + " " + time + " 		" + Q.elementAt(i).get_vec().elementAt(0).get_name()
											  + " 		" + (time + Q.elementAt(i).get_vec().elementAt(0).get_bursttime()) +";";
							
							time += Q.elementAt(i).get_vec().elementAt(0).get_bursttime();
							Q.elementAt(i).get_vec().elementAt(0)
									.set_turnaroundtime(time - Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime());
							bigvec.addElement(Q.elementAt(i).get_vec().elementAt(0));

							Q.elementAt(i).get_vec().remove(0);
							
							if (Q.elementAt(i).get_vec().isEmpty()) 
							{
								Q.remove(i);
							}
							break;
						} 
						else 
						{
							if (!check) 
							{
								time++;
							}
							
							check = false;
							Process = Process + " " + time + " 		" + Q.elementAt(i).get_vec().elementAt(0).get_name()
									+ " 		"+ min+";";
							Q.elementAt(i).get_vec().elementAt(0)
									.set_bursttime(Q.elementAt(i).get_vec().elementAt(0).get_bursttime() + time - min);
							time = min;
							break;
						}
					}
					if (min > Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime()) 
					{
						min = Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime();
					}
				} 
				/******************************
				 * 		 SJF ALGORITHM
				 *****************************/
				else if (Q.elementAt(i).get_algorithm().equals("SJF")) 
				{
					shortestjob = 1000;
					for (int j = 0; j < Q.elementAt(i).get_vec().size(); j++) 
					{
						if (Q.elementAt(i).get_vec().elementAt(j).get_arrivaltime() <= time) 
						{
							if (shortestjob > Q.elementAt(i).get_vec().elementAt(j).get_bursttime()) 
							{
								shortestjob = Q.elementAt(i).get_vec().elementAt(j).get_bursttime();
								index = j;
							}
						}
					}
					if (Q.elementAt(i).get_vec().elementAt(index).get_arrivaltime() <= time) 
					{
						check1 = false;
						if (min >= time + 1 + Q.elementAt(i).get_vec().elementAt(index).get_bursttime()) 
						{
							if (!check) 
							{
								time++;
							}
							check = false;
							Process = Process + " " + time + "		" + Q.elementAt(i).get_vec().elementAt(index).get_name()
									          + " 		" + (time + Q.elementAt(i).get_vec().elementAt(index).get_bursttime())+";";
							
							time += Q.elementAt(i).get_vec().elementAt(index).get_bursttime();
							Q.elementAt(i).get_vec().elementAt(index).set_turnaroundtime(time - Q.elementAt(i).get_vec().elementAt(index).get_arrivaltime());
							bigvec.addElement(Q.elementAt(i).get_vec().elementAt(index));
							Q.elementAt(i).get_vec().remove(index);
							
							if (Q.elementAt(i).get_vec().isEmpty()) 
							{
								Q.remove(i);
							}
							break;
						}
						else 
						{
							if (!check) {
								time++;
							}
							check = false;
							Process = Process + " " + time + " 		"+ Q.elementAt(i).get_vec().elementAt(index).get_name()+ " 		"+ min+";";
							Q.elementAt(i).get_vec().elementAt(index).set_bursttime(Q.elementAt(i).get_vec().elementAt(index).get_bursttime() + time - min);
							time = min;
							break;
						}
					}
					if (min > Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime()) 
					{
						min = Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime();
					}
				} 
				
				/******************************
				 * 		  RR ALGORITHM
				 *****************************/
				else if (Q.elementAt(i).get_algorithm().equals("RR")) 
				{
					if (Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime() <= time)
					{
						check1 = false;
						if (Q.elementAt(i).get_vec().elementAt(0).get_bursttime() > 2)
						{
							if (min >= time + 3)
							{
								if (!check)
								{
									time++;
								}
								check = false;
								Process = Process + " " + time + " 		"+ Q.elementAt(i).get_vec().elementAt(0).get_name()
										+ " 		" + (time + 2)+";";
								time += 2;
								Q.elementAt(i).get_vec().elementAt(0)
										.set_bursttime(Q.elementAt(i).get_vec().elementAt(0).get_bursttime() - 2);

								for (int k = 0; k < Q.elementAt(i).get_vec().size(); k++) 
								{
									if (Q.elementAt(i).get_vec().elementAt(k).get_arrivaltime() > time) 
									{
										Q.elementAt(i).get_vec().add(k, Q.elementAt(i).get_vec().elementAt(0));
										Q.elementAt(i).get_vec().remove(0);
										break;
									} 
									else if (Q.elementAt(i).get_vec().size() - k == 1) 
									{
										Q.elementAt(i).get_vec().add(k + 1, Q.elementAt(i).get_vec().elementAt(0));
										Q.elementAt(i).get_vec().remove(0);
									}
								}
								break;
							} 
							
							else 
							{
								if (!check) 
								{
									time++;
								}
								check = false;
								Process = Process + " " + time + " 		" + Q.elementAt(i).get_vec().elementAt(0).get_name()+ " 		" + (time + 1)+";";
								time += 1;
								Q.elementAt(i).get_vec().elementAt(0).set_bursttime(Q.elementAt(i).get_vec().elementAt(0).get_bursttime() - 1);
								
								for (int k = 0; k < Q.elementAt(i).get_vec().size(); k++) 
								{
									if (Q.elementAt(i).get_vec().elementAt(k).get_arrivaltime() > time) 
									{
										Q.elementAt(i).get_vec().add(k, Q.elementAt(i).get_vec().elementAt(0));
										Q.elementAt(i).get_vec().remove(0);
										break;
									} 
									else if (Q.elementAt(i).get_vec().size() - k == 1) 
									{
										Q.elementAt(i).get_vec().add(k + 1, Q.elementAt(i).get_vec().elementAt(0));
										Q.elementAt(i).get_vec().remove(0);
									}
								}
								break;
							}
						} 
						else 
						{
							if (min >= time + 1 + Q.elementAt(i).get_vec().elementAt(0).get_bursttime()|| (time + 1 + Q.elementAt(i).get_vec().elementAt(0).get_bursttime()) - min == 1)
							{
								if (!check) 
								{
									time++;
								}
								check = false;
								Process = Process + " " + time + " 		"+ Q.elementAt(i).get_vec().elementAt(0).get_name()+ " 		" + (time + Q.elementAt(i).get_vec().elementAt(0).get_bursttime())+";";
								time += Q.elementAt(i).get_vec().elementAt(0).get_bursttime();
								Q.elementAt(i).get_vec().elementAt(0).set_turnaroundtime(time - Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime());
								bigvec.addElement(Q.elementAt(i).get_vec().elementAt(0));
								Q.elementAt(i).get_vec().remove(0);
								if (Q.elementAt(i).get_vec().isEmpty()) 
								{
									Q.remove(i);
								}
								break;
							} 
							else 
							{
								if (!check) 
								{
									time++;
								}
								check = false;
								Process = Process + " " + time + " 		" + Q.elementAt(i).get_vec().elementAt(0).get_name()+ " 		" + (time + 1);
								time++;
								Q.elementAt(i).get_vec().elementAt(0).set_bursttime(Q.elementAt(i).get_vec().elementAt(0).get_bursttime() - 1);
								
								for (int k = 0; k < Q.elementAt(i).get_vec().size(); k++)
								{
									if (Q.elementAt(i).get_vec().elementAt(k).get_arrivaltime() > time) 
									{
										Q.elementAt(i).get_vec().add(k, Q.elementAt(i).get_vec().elementAt(0));
										Q.elementAt(i).get_vec().remove(0);
										break;
									} 
									else if (Q.elementAt(i).get_vec().size() - k == 1) 
									{
										Q.elementAt(i).get_vec().add(k + 1, Q.elementAt(i).get_vec().elementAt(0));
										Q.elementAt(i).get_vec().remove(0);
									}
								}
								break;
							}
						}
					}
					if (min > Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime()) 
					{
						min = Q.elementAt(i).get_vec().elementAt(0).get_arrivaltime();
					}
				}
			}
			if (check1) 
			{
				time++;
			}
		}
		return bigvec;
	}
/****************************************************************************************************************/
	public static void writeTofile(Vector <Process> processes, String allProcesses)
	{
		File file = new File ("Output.txt");
		String temp="";
		if (file.exists() && file.canWrite())
		{
			
			try 
			{
				
				FileWriter writer = new FileWriter (file,true);
				writer.write("Processes Order According To Its Execution Order: ");
				writer.write(System.lineSeparator());
				writer.write("******************************************");
				writer.write(System.lineSeparator());
				writer.write("Start Time         Process Name            End Time");
				writer.write(System.lineSeparator());
				
				String [] arr = allProcesses.split(";");
				for (int i=0; i<arr.length;i++)
				{
					writer.write(arr[i]);
					writer.write(System.lineSeparator());
				}
				
				writer.write(System.lineSeparator());
				writer.write(System.lineSeparator());
/********************************************************************************************/

				writer.write("Processes Turn-Around Time: ");
				writer.write(System.lineSeparator());
				writer.write("******************************************");
				writer.write(System.lineSeparator());
				for (int i=0;i<processes.size();i++)
				{
					
					temp = processes.get(i).get_name() + " with turnaround time = " + processes.get(i).get_turnaroundtime();
					writer.write(temp);
					writer.write(System.lineSeparator());
					 
				}
				writer.write(System.lineSeparator());
/********************************************************************************************/

				writer.write("Average Turn-Around Time Per Queue: ");
				writer.write(System.lineSeparator());
				writer.write("*********************************");
				writer.write(System.lineSeparator());
				//PRINT AVERAGE TURN-AROUND Per QUEUE
				for(int i=0;i<processes.size();i++)
				{
					String tempString = "";
					double counter=1;
					String name = processes.elementAt(i).getQueueName();
					double turn = processes.elementAt(i).get_turnaroundtime();
					for(int j=i+1;j<processes.size();j++){
						if(processes.elementAt(j).getQueueName().equals(name)){
							turn+=processes.elementAt(j).get_turnaroundtime();
							processes.removeElementAt(j);
							j--;
							counter++;
						}
					}
					tempString = name +" "+(turn/counter) + " ms";
					writer.write(tempString);
					writer.write(System.lineSeparator());
					
				}
				
				writer.close();
			} 
			catch (IOException e) {
				
			}
		}
		else
		{
			System.out.println("Error while writing to file!");
		}
		
		
	}
/*******************************************************************************************************************************/
	public static void main(String[] args) {
		Vector<Process> vec = new Vector<Process>();
		vec=CPU(ReadData());
		writeTofile(vec,Process);
		
	}

}
