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
    public void constructor_cuentaInicializadaTieneElSaldoIndicado(){
        // Assert
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);

        // Act
        int cantidadObtenida = cuenta.getBalance();

        // Assert
        assertEquals(cantidadInicial,cantidadObtenida,"Devuelve una cantidad distinta a la introducida");
    }

    @Test
    public void withdraw_argumentoPositivo_quitaElDineroIndicado(){
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
    public void withdraw_cantidadSuperiorAInicial_noQuitaDinero(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadBorrada = 1000;

        int cantidadRestante;
        boolean borrado = cuenta.withdraw(cantidadBorrada);
        cantidadRestante = cuenta.getBalance();

        assertFalse(borrado);
        assertEquals(cantidadRestante, cantidadInicial);
    }

    @Test
    public void withdraw_argumentoCero_noQuitaDinero(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadBorrada = 0;

        int cantidadRestante;
        cuenta.withdraw(cantidadBorrada); // Podría devolver true o false, ya que no retira dinero, motivo para false, que es lo que se pide, motivo para true
        cantidadRestante = cuenta.getBalance();

        assertEquals(cantidadRestante, cantidadInicial);
    }

    @Test
    public void withdraw_argumentoNegativo_noQuitaDinero(){ // Debería fallar, ya que no se comprueba el signo en withdraw
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadBorrada = -100;

        int cantidadRestante;
        boolean borrado = cuenta.withdraw(cantidadBorrada); // No debería retirar cantidades negativas de saldo
        cantidadRestante = cuenta.getBalance();

        assertFalse(borrado);
        assertEquals(cantidadRestante, cantidadInicial, "Se ha cambiado el dinero de la cuenta, al intentar retirar una cantidad negativa");
    }

    @Test
    public void deposit_argumentoPositivo_depositaElDineroYDevuelveElTotal(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadDepositada = 0;

        int total = cuenta.deposit(cantidadDepositada);

        assertEquals(total, cantidadInicial + cantidadDepositada);
    }

    @Test
    public void deposit_argumentoCero_noDepositaNada(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadDepositada = 0;

        int total = cuenta.deposit(cantidadDepositada);

        assertEquals(total, cantidadInicial);
    }

    @Test
    public void deposit_argumentoNegativo_lanzaExcepcion(){
        int cantidadInicial = 100;
        cuenta = new BankAccount(cantidadInicial);
        int cantidadDepositada = -10;

        assertThrows(RuntimeException.class,() -> cuenta.deposit(cantidadDepositada));
    }

    @Test
    public void payment_argumentosPositivos_calculaElPrecio() {
        int pagos = 20;
        double total = 100, interes = 1.01,
                cantidadEsperada = total*(interes*Math.pow((1+interes), pagos)/(Math.pow((1+interes), pagos)-1));

        double cantidadObtenida = cuenta.payment(total,interes,pagos);

        assertEquals(cantidadEsperada,cantidadObtenida);
    }

    @Test
    public void payment_totalCero_devuelveCero() {
        int pagos = 20;
        double total = 0, interes = 1.01,
                cantidadEsperada = 0;

        double cantidadObtenida = cuenta.payment(total,interes,pagos);

        assertEquals(cantidadEsperada,cantidadObtenida);
    }

    @Test
    public void payment_totalNegativo_lanzaExcepcion() { // Fallan porque no se comprueban, pero no deberían devolver ningún valor ya que no tienen sentido
        int pagos = 20;
        double total = -1, interes = 1.01,
                cantidadEsperada = 0;

        assertThrows(RuntimeException.class,() -> cuenta.payment(total,interes,pagos));
    }

    @Test
    public void payment_interesNegativo_lanzaExcepcion() {
        int pagos = 20;
        double total = 100, interes = -1,
                cantidadEsperada = 0;

        assertThrows(RuntimeException.class,() -> cuenta.payment(total,interes,pagos));
    }

    @Test
    public void payment_pagosNegativo_lanzaExcepcion() {
        int pagos = -1;
        double total = 100, interes = 1.01,
                cantidadEsperada = 0;

        assertThrows(RuntimeException.class,() -> cuenta.payment(total,interes,pagos));
    }

    @Test
    public void pending_argumentosPositivos_calculaElPrecio() {
        double cantidad = 100, interes = 1.01;
        int pagos = 10, mes = 3;

        double cantidadObtenida = cuenta.pending(cantidad,interes,pagos,mes);

        assertTrue(cantidadObtenida > 0);
    }

    @Test
    public void pending_cantidadNegativa_lanzaExcepcion() {
        double cantidad = -1, interes = 1.01;
        int pagos = 10, mes = 3;

        assertThrows(RuntimeException.class, () -> cuenta.pending(cantidad,interes,pagos,mes));
    }

    @Test
    public void pending_interesNegativo_lanzaExcepcion() {
        double cantidad = 100, interes = -1;
        int pagos = 10, mes = 3;

        assertThrows(RuntimeException.class, () -> cuenta.pending(cantidad,interes,pagos,mes));
    }

    @Test
    public void pending_pagosNegativos_lanzaExcepcion() {
        double cantidad = 100, interes = 1.01;
        int pagos = -1, mes = 3;

        assertThrows(RuntimeException.class, () -> cuenta.pending(cantidad,interes,pagos,mes));
    }

    @Test
    public void pending_mesesNegativos_lanzaExcepcion() {
        double cantidad = 100, interes = 1.01;
        int pagos = 10, mes = -1;

        assertThrows(RuntimeException.class, () -> cuenta.pending(cantidad,interes,pagos,mes));
    }
}
