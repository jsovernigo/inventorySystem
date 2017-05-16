/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentthree;

/**
 *
 * @author julian
 */
public class InvalidPriceException extends Exception {

    /**
     * Creates a new instance of <code>InvlaidPriceException</code> without
     * detail message.
     */
    public InvalidPriceException() {
    }

    /**
     * Constructs an instance of <code>InvlaidPriceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidPriceException(String msg) {
        super(msg);
    }
}
