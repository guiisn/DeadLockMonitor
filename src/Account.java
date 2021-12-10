public class Account implements Runnable {

	MonitorDeadlock monitor;
	Thread t;

	final int execution_counter = 10;

	public Account(String name, MonitorDeadlock monitor) { // iniciando o método
		this.monitor = monitor;
		t = new Thread(this, name);
		t.start();
	}

	@Override
	public void run() { // inicio da threads

		// verificação pelo nome da thread
		if (t.getName().equalsIgnoreCase("contaCredito")) {
			for (int i = 0; i < execution_counter; i++) {
				// se a thread estiver executando:
				monitor.credit_account(true);
			}
			monitor.credit_account(false);
		} else {
			// se a a o nome da conta que a thread está executando for diferente de
			// contaCredito, ele ja inicia a de contaDebito
			for (int i = 0; i < execution_counter; i++) {
				monitor.debit_account(true);
			}
			monitor.debit_account(false);
		}

	}
}
