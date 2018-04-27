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
public class CreateNewMovieException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewMovieException</code> without
     * detail message.
     */
    public CreateNewMovieException() {
    }

    /**
     * Constructs an instance of <code>CreateNewMovieException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewMovieException(String msg) {
        super(msg);
    }
}
