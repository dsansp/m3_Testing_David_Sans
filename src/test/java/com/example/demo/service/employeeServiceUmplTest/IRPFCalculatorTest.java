package com.example.demo.service.employeeServiceUmplTest;

import com.example.demo.service.IRPFCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IRPFCalculatorTest {
//error cannot find symbol


        IRPFCalculator calculator = new IRPFCalculator();

        @DisplayName("Irpf correcto")
        @Test
        void calculateIRPFTest(){

            double resultIrpf = calculator.calculateIRPF(100);
            assertEquals(15D, resultIrpf);
        }
        @DisplayName("comprobamos valor 0")
        @Test
        void calculateIRPFZeroTest(){

            double resultIrpf = calculator.calculateIRPF(0);
            assertEquals(0, resultIrpf);
        }
        @DisplayName("Comprobamos IRPF negativo")
        @Test
        void calculateIRPFNegativeTest(){

            double resultIrfp = calculator.calculateIRPF(-100);
            assertEquals(-15, resultIrfp);
        }



        }
