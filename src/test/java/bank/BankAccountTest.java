package bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount cuenta;

    @Test
    public void Constructor_CuentaInicializadaTieneElSaldoIndicado(){
        // Assert
        int cantidadIntroducida = 100;
        cuenta = new BankAccount(100);

        // Act
        int cantidadObtenida = cuenta.getBalance();

        // Assert
        assertEquals(cantidadIntroducida,cantidadObtenida,"Devuelve una cantidad distinta a la introducida");
    }

}
