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
public class InvalidYearException extends Exception {

    /**
     * Creates a new instance of <code>InvalidYearException</code> without
     * detail message.
     */
    public InvalidYearException() {
    }

    /**
     * Constructs an instance of <code>InvalidYearException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidYearException(String msg) {
        super(msg);
    }
}
