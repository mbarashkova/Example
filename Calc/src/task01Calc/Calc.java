package task01Calc;

public interface Calc {
	
	void setExpression(String newExpression);

	String getExpression();

	void compute();

	Double getResult();
}