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
public class DeleteMovieException extends Exception {

    /**
     * Creates a new instance of <code>DeleteMovieException</code> without
     * detail message.
     */
    public DeleteMovieException() {
    }

    /**
     * Constructs an instance of <code>DeleteMovieException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DeleteMovieException(String msg) {
        super(msg);
    }
}
