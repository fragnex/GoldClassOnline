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
public class CreateNewCalendarDayException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewCalendarDayException</code>
     * without detail message.
     */
    public CreateNewCalendarDayException() {
    }

    /**
     * Constructs an instance of <code>CreateNewCalendarDayException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewCalendarDayException(String msg) {
        super(msg);
    }
}
