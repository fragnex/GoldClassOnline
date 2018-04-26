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
public class DeleteCinemaException extends Exception {

    /**
     * Creates a new instance of <code>DeleteCinemaException</code> without
     * detail message.
     */
    public DeleteCinemaException() {
    }

    /**
     * Constructs an instance of <code>DeleteCinemaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DeleteCinemaException(String msg) {
        super(msg);
    }
}
