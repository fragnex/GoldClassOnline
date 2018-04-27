/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author shaunphua
 */
public class MovieException extends Exception {

    /**
     * Creates a new instance of <code>MovieException</code> without detail
     * message.
     */
    public MovieException() {
    }

    /**
     * Constructs an instance of <code>MovieException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MovieException(String msg) {
        super(msg);
    }
}
