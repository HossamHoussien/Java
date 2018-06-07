
public class Process implements Comparable<Process> {
	String name;
	int ArrivalTime;
	int BurstTime;
	int TurnaroundTime;
	String QueueName;

	Process() {
		this.name = "";
		this.ArrivalTime = 0;
		this.BurstTime = 0;
		this.TurnaroundTime = 0;
		this.QueueName = "";
	}

	void set_name(String n) {
		this.name = n;
	}

	void set_arrivaltime(int A) {
		this.ArrivalTime = A;
	}

	void set_bursttime(int B) {
		this.BurstTime = B;
	}

	void set_turnaroundtime(int E) {
		this.TurnaroundTime = E;
	}

	int get_turnaroundtime() {
		return this.TurnaroundTime;
	}

	String get_name() {
		return this.name;
	}

	int get_arrivaltime() {
		return this.ArrivalTime;
	}

	int get_bursttime() {
		return this.BurstTime;
	}

	public String getQueueName() {
		return QueueName;
	}

	public void setQueueName(String queueName) {
		QueueName = queueName;
	}

	public void printProcess() {
		System.out.println("Process Name: " + name);
		System.out.println("Process Arrival Time: " + ArrivalTime);
		System.out.println("Process Burst Time: " + BurstTime + "\n");
	}

	public int compareTo(Process other) {
		if (this.ArrivalTime > other.get_arrivaltime()) {
			return 1;
		} else if (this.ArrivalTime < other.get_arrivaltime()) {
			return -1;
		} else {
			if (this.BurstTime > other.get_bursttime()) {
				return 1;
			} else if (this.BurstTime < other.get_bursttime()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
