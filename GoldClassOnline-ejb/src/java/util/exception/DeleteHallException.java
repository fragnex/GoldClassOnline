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
public class DeleteHallException extends Exception {

    /**
     * Creates a new instance of <code>DeleteHallException</code> without detail
     * message.
     */
    public DeleteHallException() {
    }

    /**
     * Constructs an instance of <code>DeleteHallException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DeleteHallException(String msg) {
        super(msg);
    }
}
