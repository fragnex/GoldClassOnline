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
public class DeleteScreeningScheduleException extends Exception {

    /**
     * Creates a new instance of <code>DeleteScreeningScheduleException</code>
     * without detail message.
     */
    public DeleteScreeningScheduleException() {
    }

    /**
     * Constructs an instance of <code>DeleteScreeningScheduleException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DeleteScreeningScheduleException(String msg) {
        super(msg);
    }
}
