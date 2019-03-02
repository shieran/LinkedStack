package by.hancharou;

import org.junit.Test;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MyStackTest {

    @Test
    public void toStingAndPushTest(){
        MyStack<Integer> numbers = new MyStack<>();
        numbers.push(123);
        numbers.push(12);
        numbers.push(3);
        assertEquals("This Stack: 3 12 123", numbers.toString());
    }

    @Test
    public void popTest(){
        MyStack<Integer> numbers = new MyStack<>();
        numbers.push(123);
        numbers.push(1);
        numbers.push(12);
        assertEquals("12", numbers.pop().toString());
        assertEquals("1", numbers.pop().toString());
        assertEquals("123", numbers.pop().toString());
    }

    @Test (expected = NoSuchElementException.class)
    public void popTest2(){
        MyStack<Integer> numbers = new MyStack<>();
        numbers.push(null);
        numbers.pop();
    }

    @Test
    public void sizeTest(){
        MyStack<Integer> numbers = new MyStack<>();
        numbers.push(123);
        numbers.push(123);
        numbers.push(123);
        assertEquals(3, numbers.size());
    }

    @Test
    public void equalsTest(){
        MyStack<Integer> numbers = new MyStack<>();
        numbers.push(1);
        numbers.push(2);

        MyStack<Integer> numbers2 = new MyStack<>();
        numbers2.push(1);
        numbers2.push(2);

        MyStack<Integer> numbers3 = new MyStack<>();
        numbers3.push(3);
        numbers3.push(2);

        MyStack<Integer> numbers6 = new MyStack<>();
        numbers6.push(3);
        numbers6.push(3);
        numbers6.push(3);
        numbers6.push(3);
        numbers6.push(3);

        MyStack<Integer> numbers4 = numbers;
        MyStack<Integer> numbers5 = null;
        Fraction fraction = new Fraction(2);



        assertTrue(numbers.equals(numbers2));
        assertTrue(numbers.equals(numbers));
        assertFalse(numbers.equals(numbers3));
        assertTrue(numbers.equals(numbers4));
        assertFalse(numbers.equals(fraction));
        assertFalse(numbers.equals(numbers5));
        assertFalse(numbers.equals(numbers6));


    }

    @Test
    public void hashCodeTest(){
        MyStack<Integer> numbers = new MyStack<>();
        numbers.push(987654321);
        numbers.push(123456789);

        MyStack<Integer> numbers2 = new MyStack<>();
        numbers2.push(987654321);
        numbers2.push(123456789);

        MyStack<Integer> numbers3 = new MyStack<>();
        numbers3.push(123123123);
        numbers3.push(356457);

        assertTrue(numbers.hashCode() == numbers2.hashCode());
        assertFalse(numbers.hashCode() == numbers3.hashCode());
    }


        @Test (expected = EmptyStackException.class)
    public void peekTest(){
        MyStack<String> stack = new MyStack<>();
        stack.peek();
    }



}
