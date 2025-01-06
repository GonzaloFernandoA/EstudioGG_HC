package com.example.demo;

import com.estudioGG.hc.model.Registro;
import com.estudioGG.hc.utils.ListaValidator;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

//@SpringBootTest
class DemoApplicationTests {

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testSample() {

        Assertions.assertEquals(0, 0);
    }

    @Test
    void ValidationListTest() {

        ListaValidator validator = new ListaValidator();

        List<Registro> lista1 = new ArrayList();
        List<Registro> lista2 = new ArrayList();

        Boolean result = validator.validarListasIgualesSinOrden(lista1, lista2);
        Assertions.assertTrue(result);
    }

    @Test
    void ValidationListTest2() {

        ListaValidator validator = new ListaValidator();

        List<Registro> lista1 = new ArrayList();
        List<Registro> lista2 = new ArrayList();

        lista1.add(new Registro("COD1", "PES2"));

        Boolean result = validator.validarListasIgualesSinOrden(lista1, lista2);
        Assertions.assertFalse(result);
    }

    @Test
    void ValidationListTest3() {

        ListaValidator validator = new ListaValidator();

        List<Registro> lista1 = new ArrayList();
        List<Registro> lista2 = new ArrayList();

        lista1.add(new Registro("COD1", "PES2"));
        lista2.add(new Registro("COD1", "PES2"));

        Boolean result = validator.validarListasIgualesSinOrden(lista1, lista2);
        Assertions.assertTrue(result);
    }

    @Test
    void ValidationListTest4() {

        ListaValidator validator = new ListaValidator();

        List<Registro> lista1 = new ArrayList();
        List<Registro> lista2 = new ArrayList();

        lista1.add(new Registro("COD2", "PES4"));
        lista1.add(new Registro("COD1", "PES2"));
        lista2.add(new Registro("COD1", "PES2"));

        lista2.add(new Registro("COD2", "PES4"));
        Boolean result = validator.validarListasIgualesSinOrden(lista1, lista2);
        Assertions.assertTrue(result);
    }

    @Test
    void ValidationListTest5() {

        ListaValidator validator = new ListaValidator();

        List<Registro> lista1 = new ArrayList();
        List<Registro> lista2 = new ArrayList();

        lista1.add(new Registro("COD2", "PES4"));
        lista1.add(new Registro("COD1", "PES2"));
        lista2.add(new Registro("COD1", "PES2"));

        lista2.add(new Registro("COD2", "PESX"));
        Boolean result = validator.validarListasIgualesSinOrden(lista1, lista2);
        Assertions.assertFalse(result);
    }
    
        @Test
    void ValidationListTest6() {

        ListaValidator validator = new ListaValidator();

        List<Registro> lista1 = new ArrayList();
        List<Registro> lista2 = new ArrayList();

        lista1.add(new Registro("COD2", "PES4"));
        lista1.add(new Registro("COD1", "PES2"));
        lista2.add(new Registro("COD1", "PES2"));

        lista2.add(new Registro("COD2", "PES4"));
        lista2.add(new Registro("CODX", "PESX")); 
        
        Boolean result = validator.validarListasIgualesSinOrden(lista1, lista2);
        Assertions.assertFalse(result);
    }
}
