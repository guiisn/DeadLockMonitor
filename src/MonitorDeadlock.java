public class MonitorDeadlock {

	boolean credit_account;
	// instancia para futura verificação

	synchronized void credit_account(boolean thread_running) {

		if (!thread_running) {
			// se a thread não estiver mais executando, o método é parado
			credit_account = true;
			notify();
			return;
		}

		System.out.println("[CONTA]: Conta de Crédito");

		credit_account = true;

		notify();

		try {
			while (credit_account) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized void debit_account(boolean thread_running) {

		if (!thread_running) {
			credit_account = false;
			notify();
			return;
		}

		System.out.println("[CONTA]: Conta de Débito");

		credit_account = false;

		notify();

		try {
			while (!credit_account) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
