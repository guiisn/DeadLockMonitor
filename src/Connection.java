public class Connection {

	public static void main(String[] args) {

		// chamando o monitor, a contaCredito e a debito
		MonitorDeadlock monitor = new MonitorDeadlock();
		Account credito = new Account("contaCredito", monitor);
		Account debito = new Account("contaDebito", monitor);

		try {
			credito.t.join(); // join() para aguardar a thread terminar a execução
			debito.t.join(); // join() para aguardar a thread terminar a execução
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
