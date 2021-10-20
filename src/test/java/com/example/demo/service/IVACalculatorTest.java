package com.example.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IVACalculatorTest {
    IVACalculator calculator = new IVACalculator();

@DisplayName("Iva correcto")
        @Test
        void calculateIVATest(){

            double result = calculator.calculateIVA(100);
            assertEquals(21, result);
        }
@DisplayName("comprobamos valor 0")
        @Test
        void calculateIVAZeroTest(){

            double result = calculator.calculateIVA(0);
            assertEquals(0, result);
        }
@DisplayName("Comprobamos Iva negativo")
        @Test
        void calculateIVANegativeTest(){

            double result = calculator.calculateIVA(-100);
            assertEquals(-21, result);
        }
    }
