package src.model;

import java.io.Serializable;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public class Context implements Serializable {
	private static final long serialVersionUID = -9006344622797729357L;
	private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}