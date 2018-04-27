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
public class CalendarDayNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CalendarDayNotFoundException</code>
     * without detail message.
     */
    public CalendarDayNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CalendarDayNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CalendarDayNotFoundException(String msg) {
        super(msg);
    }
}
