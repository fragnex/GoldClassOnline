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
public class CreateNewScreeningScheduleException extends Exception {

    /**
     * Creates a new instance of
     * <code>CreateNewScreeningScheduleException</code> without detail message.
     */
    public CreateNewScreeningScheduleException() {
    }

    /**
     * Constructs an instance of
     * <code>CreateNewScreeningScheduleException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewScreeningScheduleException(String msg) {
        super(msg);
    }
}
