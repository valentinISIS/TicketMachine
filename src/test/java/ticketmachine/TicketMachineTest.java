package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	// S3: on n’imprime pas leticket si le montant inséré est insuffisant
	void notPrintIfMoneyIsInsufficient(){
		assertFalse(machine.printTicket(), "Le ticket ne doit pas pouvoir être imprimer");
	}

	@Test
	// S4: on imprime le ticket si le montant inséré est suffisant
	void printIfMoneyIsSufficient(){
		machine.insertMoney(50);
		assertTrue(machine.printTicket(), "Le ticket doit s'imprimer");
	}

	@Test
	// S5: Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void decreaseBalanceWhenPrintTicket(){
		machine.insertMoney(50);
		machine.printTicket();
		assertEquals(machine.getBalance(), 50-PRICE, "La balance doit être mise à jour lors de l'impression d'un ticket");
	}

	@Test
	// S6: le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void collectMoneyWhenTicketIsPrint(){
		machine.insertMoney(50);
		assertEquals(machine.getTotal(), 0, "Le total doit être mise à jour lors de l'impression d'un ticket");
		machine.printTicket();
		assertEquals(machine.getTotal(), PRICE, "Le total doit être mise à jour lors de l'impression d'un ticket");
	}

	@Test
	// S7 : refund()rendcorrectement la monnaie
	void refundMoney(){
		machine.insertMoney(20);
		assertEquals(machine.refund(), 20, "La machine ne rend pas correctement la monnaie");
	}

	@Test
	// S8 : refund()remet la balance à zéro
	void refundMoneySetBalanceToZero(){
		machine.insertMoney(20);
		machine.refund();
		assertEquals(machine.getBalance(), 0, "La machine ne réinitialise pas la balance correctement");
	}
}
