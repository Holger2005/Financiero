package com.krakedev.financiero.servico;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servico.Banco;

public class TestBanco {

	@Test
    public void testCrearCuentaCodigosConsecutivos() {
        // 1. Instanciar el servicio Banco (Punto 5)
        Banco banco = new Banco();
        
        // 2. Crear clientes de prueba
        Cliente cliente1 = new Cliente("1722334455", "Juan", "Pérez");
        Cliente cliente2 = new Cliente("1755443322", "María", "López");
        
        // 3. Ejecutar el método crearCuenta (Punto 6)
        Cuenta cuenta1 = banco.crearCuenta(cliente1);
        Cuenta cuenta2 = banco.crearCuenta(cliente2);
        
        // 4. Validaciones usando únicamente assertEquals
        // Validar que se generen códigos consecutivos empezando desde "1000"
        assertEquals("1000", cuenta1.getId());
        assertEquals("1001", cuenta2.getId());
        
        // Validar que el contador interno del banco se haya incrementado a 1002
        assertEquals(1002, banco.getUltimoCodigo());
        
        // Validar que los propietarios se hayan asignado correctamente
        assertEquals(cliente1, cuenta1.getPropetario());
        assertEquals(cliente2, cuenta2.getPropetario());
        
	}
	
	@Test
    public void testDepositarMontoValido() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1001"); // Inicializa saldoActual en 0
        
        // Se deposita un monto mayor a 0
        boolean resultado = banco.depositar(50.0, cuenta);
        
        // Validaciones
        assertTrue(resultado, "El depósito debería ser exitoso para un monto mayor a 0");
        assertEquals(50.0, cuenta.getSaldoActual(), "El saldo actual debería reflejar el monto depositado");
    }

    @Test
    public void testDepositarMontoInvalido() {
        Banco banco = new Banco();
        Cuenta cuenta = new Cuenta("1002"); // Inicializa saldoActual en 0
        
        // Se intenta depositar un monto inválido (menor o igual a 0)
        boolean resultado = banco.depositar(-10.0, cuenta);
        
        // Validaciones
        assertFalse(resultado, "El depósito debería fallar para un monto menor o igual a 0");
        assertEquals(0.0, cuenta.getSaldoActual(), "El saldo de la cuenta no debería cambiar");
    }
}