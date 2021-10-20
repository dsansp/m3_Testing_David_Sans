package com.example.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IRPFCalculatorTest {
//error cannot find symbol


        IRPFCalculator calculator = new IRPFCalculator();

        @DisplayName("Irpf correcto")
        @Test
        void calculateIRPFTest(){

            double result = calculator.calculateIRPF(100);
            assertEquals(15D, result);
        }
        @DisplayName("comprobamos valor 0")
        @Test
        void calculateIRPFZeroTest(){

            double result = calculator.calculateIRPF(0);
            assertEquals(0, result);
        }
        @DisplayName("Comprobamos Iva negativo")
        @Test
        void calculateIRPFNegativeTest(){

            double result = calculator.calculateIRPF(-100);
            assertEquals(-15, result);
        }



        }
