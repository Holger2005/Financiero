package com.krakedev.financiero.servico;
import static org.junit.Assert.assertEquals;

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
}