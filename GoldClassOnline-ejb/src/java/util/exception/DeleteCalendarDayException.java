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
public class DeleteCalendarDayException extends Exception {

    /**
     * Creates a new instance of <code>DeleteCalendarDayException</code> without
     * detail message.
     */
    public DeleteCalendarDayException() {
    }

    /**
     * Constructs an instance of <code>DeleteCalendarDayException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DeleteCalendarDayException(String msg) {
        super(msg);
    }
}
