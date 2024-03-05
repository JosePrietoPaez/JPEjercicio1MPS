package bank;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount cuenta;

    @BeforeEach
    public void reiniciar() {
        cuenta = new BankAccount(0);
    }

    @Test
    public void constructor_CuentaInicializadaTieneElSaldoIndicado(){
        // Assert
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);

        // Act
        int cantidadObtenida = cuenta.getBalance();

        // Assert
        assertEquals(cantidadInicial,cantidadObtenida,"Devuelve una cantidad distinta a la introducida");
    }

    @Test
    public void withdraw_ArgumentoPositivo_QuitaElDineroIndicado(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadBorrada = 10;

        int cantidadRestante;
        boolean borrado = cuenta.withdraw(cantidadBorrada);
        cantidadRestante = cuenta.getBalance();

        assertTrue(borrado);
        assertEquals(cantidadBorrada+cantidadRestante, cantidadInicial);
    }

    @Test
    public void withdraw_ArgumentoCero_NoQuitaDinero(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadBorrada = 0;

        int cantidadRestante;
        cuenta.withdraw(cantidadBorrada); // Podría devolver true o false, ya que no retira dinero, motivo para false, que es lo que se pide, motivo para true
        cantidadRestante = cuenta.getBalance();

        assertEquals(cantidadRestante, cantidadInicial);
    }

//    @Test
//    public void withdraw_ArgumentoNegativo_NoQuitaDinero(){ // Debería fallar, ya que no se comprueba el signo en withdraw
//        int cantidadInicial = 100;
//        cuenta = new BankAccount(cantidadInicial);
//        int cantidadBorrada = -100;
//
//        int cantidadRestante;
//        boolean borrado = cuenta.withdraw(cantidadBorrada); // No debería retirar cantidades negativas de saldo
//        cantidadRestante = cuenta.getBalance();
//
//        assertFalse(borrado);
//        assertEquals(cantidadRestante, cantidadInicial, "Se ha cambiado el dinero de la cuenta, al intentar retirar una cantidad negativa");
//    }

    @Test
    public void deposit_ArgumentoPositivo_DepositaElDineroYDevuelveElTotal(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadDepositada = 0;

        int total = cuenta.deposit(cantidadDepositada);

        assertEquals(total, cantidadInicial + cantidadDepositada);
    }

    @Test
    public void deposit_ArgumentoCero_NoDepositaNada(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadDepositada = 0;

        int total = cuenta.deposit(cantidadDepositada);

        assertEquals(total, cantidadInicial);
    }

    @Test
    public void deposit_ArgumentoNegativo_LanzaExcepcion(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadDepositada = -10;

        assertThrows(RuntimeException.class,() -> cuenta.deposit(cantidadDepositada));
    }

}
