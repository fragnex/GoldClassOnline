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
public class CreateNewHallException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewHallException</code> without
     * detail message.
     */
    public CreateNewHallException() {
    }

    /**
     * Constructs an instance of <code>CreateNewHallException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewHallException(String msg) {
        super(msg);
    }
}
