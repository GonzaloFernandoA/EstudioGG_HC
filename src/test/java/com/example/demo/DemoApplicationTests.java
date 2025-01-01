package com.example.demo;

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

        Assertions.assertEquals(0,0);
    }

}
