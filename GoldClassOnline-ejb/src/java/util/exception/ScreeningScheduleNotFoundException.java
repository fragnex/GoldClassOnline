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
public class ScreeningScheduleNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ScreeningScheduleNotFoundException</code>
     * without detail message.
     */
    public ScreeningScheduleNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ScreeningScheduleNotFoundException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ScreeningScheduleNotFoundException(String msg) {
        super(msg);
    }
}
