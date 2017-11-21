package src.model;

import java.io.Serializable;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public class Human implements Strategy, Serializable {
	private static final long serialVersionUID = 5966684606001314091L;

	@Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}