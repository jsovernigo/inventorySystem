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
public class InvalidProductIDException extends Exception {

    /**
     * Creates a new instance of <code>InvalidProductIDException</code> without
     * detail message.
     */
    public InvalidProductIDException() {
    }

    /**
     * Constructs an instance of <code>InvalidProductIDException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidProductIDException(String msg) {
        super(msg);
    }
}
