package com.tinylan.fibonacci;

import java.math.BigInteger;

/**
 * Handles the mathematics of the application.
 *
 * @author Justin Reherman
 */
public class FibMath extends StartFib {

    private static final long serialVersionUID = 1L;

    /**
     * Calculate fibonacci numbers using a loop
     *
     * @param number type long integer
     *
     * @return       type long integer
     */
    public static long fibonacciLoop(long number){
        // Immediately return results for the 1st and 2nd term
        // These are used to "prime" the loop
        if(number == 1 || number == 2) {
            return 1;
        }

        // "Prime" the loop by providing the first two terms
        long fibo1=1, fibo2=1, fibonacci=1;

        // Loop "number" times to get the result
        for(long i=3; i<=number; i++){
            // Fibonacci number is sum of the two previous numbers
            fibonacci = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibonacci;
        }

        // Return the result
        return fibonacci;
    }

    /**
     * Calculate large fibonacci numbers using a loop
     *
     * @param number    type long integer
     *
     * @return          type long integer
     */
    public static BigInteger fibonacciBig(long number){
        // A special class to calculate a fibonacci term where n is equal to or larger than 94.

        // Immediately return results for the 94th and 95th term
        // These are used to "prime" the loop
        if(number == 1) {
            return new BigInteger("12200160415121876738");
        } else if(number == 2) {
            return new BigInteger("19740274219868223167");
        }

        // "Prime" the formula by providing the first two terms
        BigInteger fibo94 = new BigInteger("12200160415121876738"),
        fibo95 = new BigInteger("19740274219868223167"),
        fibonacci = new BigInteger("19740274219868223167");

        // Loop "number" times to get the result
        for(long i=3; i<=number; i++) {
            // Fibonacci number is sum of two previous numbers
            fibonacci = fibo94.add(fibo95);
            fibo94 = fibo95;
            fibo95 = fibonacci;
        }

        // Return the result
        return fibonacci;
    }
}
