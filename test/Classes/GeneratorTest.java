package Classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void generateScore() {

        for(int i=0; i<100; i++){
            int a = (int) Math.random() * (6 + 1 - 2) + 2;
            assertTrue(a<7);
            assertTrue(a>1);
        }
    }
}