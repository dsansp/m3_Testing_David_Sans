package com.example.demo.service.Junit;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartPhoneServiceImplTest {
    @DisplayName("Comprobamos contar si pasamos null, que encuentre  y que sea el correcto")
    @Test
    void count() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        Integer num = service.count();

        assertNotNull(num);
        assertTrue(num > 0);
        assertEquals(3, num);
    }


    @Nested
class findTest {

        @DisplayName("Comprobamos que findAll no devuelva null y la cantidad correcta")
        @Test
        void findAllTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            List<SmartPhone> smartphones = service.findAll();

            assertNotNull(smartphones);
            assertEquals(3, smartphones.size());
        }

        @DisplayName("Comprobar que no sea null, que coja el ID y devuelva los datos")
        @Test
        void findOnePhone1Test() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone1 = service.findOne(1L);

            assertNotNull(phone1);
            assertEquals(1L, phone1.getId());
            assertNotNull(phone1.getName());
        }

        @DisplayName("Comprobar smartphone que no existe")
        @Test
        void findOnePhone999Test() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone1 = service.findOne(999L);

            assertNull(phone1);
        }

        @DisplayName("Comprobar excepción al buscar un smartphone nulo")
        @Test
        void findOneExceptionTest() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            // verifica si se ha lanzado una excepción
            assertThrows(
                    IllegalArgumentException.class,
                    () -> service.findOne(null)
            );
        }
    }

    @Nested
    class saveTest {
        @DisplayName("Comprobar que se asigna Id cuando pasamos Null ")
        @Test
        void saveNullTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            assertThrows(
                    IllegalArgumentException.class,
                    () -> service.save(null)
            );
        }

        @DisplayName("Comprobar que se asigna un id cuando el id que pasamos es 0")
        @Test
        void saveIdZeroTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone = new SmartPhone(0L, "Another one!",
                    new RAM(1L, "DDR4", 16),
                    new Battery(1L, 3500.0),
                    new CPU(1L, 8),
                    false,
                    new Camera(1L, "front camera", 10.0));

            assertEquals(3, service.count());
            SmartPhone result = service.save(phone);
            assertEquals(4, service.count());
            assertNotNull(result);
            assertNotNull(result.getId());
            assertEquals(4, result.getId());
        }

        @DisplayName("Comprobar que se actualiza un smartphone existente")
        @Test
        void saveUpdateTest() {

            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone = new SmartPhone(1L, "Xiaomi A9",
                    new RAM(1L, "DDR4", 16),
                    new Battery(1L, 4500.0),
                    new CPU(1L, 8),
                    false,
                    new Camera(1L, "front camera", 16.0));


            assertEquals(3, service.count());
            SmartPhone result = service.save(phone);
            assertEquals(3, service.count());

            assertEquals(1L, result.getId());

            SmartPhone phone1 = service.findOne(1L);
            assertEquals("Xiaomi A9", phone1.getName());

        }

        @DisplayName("Comprobar id negativo, no se añade un  nuevo elemento")
        @Test
        void saveNegativeIdTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

            SmartPhone phone = new SmartPhone(-9L, "Smartphone XXX",
                    new RAM(1L, "DDR4", 8),
                    new Battery(1L, 2500.0),
                    new CPU(1L, 4),
                    false,
                    new Camera(1L, "front camera", 12.5));

            assertEquals(3, service.count());

            assertThrows(
                    IllegalArgumentException.class,
                    () -> service.save(phone)
            );

            assertEquals(3, service.count());


        }
    }

    @Nested
    class  deleteTest {
        @DisplayName("comprobamos Id null al borrar")
        @Test
        void deleteNullTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            boolean result = service.delete(null);
            assertFalse(result);
        }

        @DisplayName("comprobamos Id no existe al borrar")
        @Test
        void deleteNotContainsTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            boolean result = service.delete(666L);
            assertFalse(result);
        }

        @DisplayName("comprobamos Id correcta al borrar")
        @Test
        void deleteOkTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            boolean result = service.delete(1L);
            assertTrue(result);
        }

        @DisplayName("comprobamos que hay registros para borrar y que despues no queda nada.")
        @Test
        void deleteAllTest() {
            SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
            assertTrue(service.count() > 0);
            service.deleteAll();
            assertEquals(0, service.count());

        }
    }
@DisplayName("comprobamos que encuentre ")
    @Test
    void findByWifi() {
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
         List<SmartPhone> phone1  = service.findByWifi(true);

        assertNotNull(service.findByWifi(true));
        assertFalse(service.findByWifi(true).isEmpty());



    }
}
