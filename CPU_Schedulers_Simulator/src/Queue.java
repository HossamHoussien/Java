import java.util.Vector;

public class Queue implements Comparable<Queue> {
	int NumOfProcess;
	int Priority;
	String QueueName;
	String Algorithm;
	Vector<Process> vec;

	Queue() {
		this.NumOfProcess = 0;
		this.Priority = 0;
		this.QueueName = "";
		this.Algorithm = "";
		this.vec = new Vector<Process>();
	}

	void set_numofprocess(int n) {
		this.NumOfProcess = n;
	}

	void set_priority(int p) {
		this.Priority = p;
	}

	void set_algorithm(String a) {
		this.Algorithm = a;
	}

	void addtovec(Process pr) {
		vec.add(pr);
	}

	void set_vec(Vector<Process> v) {
		for (int i = 0; i < v.size(); i++) {
			addtovec(v.elementAt(i));
		}
	}

	int get_numofprocess() {
		return this.NumOfProcess;
	}

	int get_priority() {
		return this.Priority;
	}

	String get_algorithm() {
		return this.Algorithm;
	}

	Vector<Process> get_vec() {
		return this.vec;
	}

	public String getQueueName() {
		return QueueName;
	}

	public void setQueueName(String queueName) {
		QueueName = queueName;
	}

	public int compareTo(Queue other) {
		if (this.Priority < other.get_priority()) {
			return 1;
		} else if (this.Priority > other.get_priority()) {
			return -1;
		} else {
			return 0;
		}
	}
}
