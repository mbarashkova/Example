package task01Calc;

public class LineCalculator implements Calc {
	String expression = null;
	Double result = null;

	public LineCalculator() {
	}

	public LineCalculator(String e) {
		this.expression = e;
	}

	public void compute() {
		if (result == null && expression != null) {
			result = plus(expression.split("\\+"));
		}
	}

	public void setExpression(String newExpression) {
		this.expression = newExpression;
		this.result = null;
	}

	public String getExpression() {
		return this.expression;
	}

	public Double getResult() {
		return result;
	}

	private double plus(String[] s) {
		Double[] slag = new Double[s.length];
		for (int i = 0; i < s.length; i++) {
			slag[i] = minus(s[i].split("\\-"));
		}

		Double r = slag[0];

		if (slag.length == 1) {
			return r;
		}

		for (int i = 1; i < s.length; i++) {
			r = r + slag[i];
		}

		return r;
	}

	private Double minus(String[] s) {
		Double[] slag = new Double[s.length];
		for (int i = 0; i < s.length; i++) {
			slag[i] = multi(s[i].split("\\*"));
		}

		Double r = slag[0];

		if (slag.length == 1) {
			return r;
		}

		for (int i = 1; i < s.length; i++) {
			r = r - slag[i];
		}

		return r;
	}

	private Double multi(String[] s) {
		Double[] slag = new Double[s.length];
		for (int i = 0; i < s.length; i++) {
			slag[i] = division(s[i].split("\\/"));
		}

		Double r = slag[0];

		if (slag.length == 1) {
			return r;
		}

		for (int i = 1; i < s.length; i++) {
			r = r * slag[i];
		}

		return r;
	}

	private Double division(String[] s) {
		Double r = Double.parseDouble(s[0]);

		if (s.length == 1) {
			return r;
		}

		for (int i = 1; i < s.length; i++) {
			r = r / Double.parseDouble(s[i]);
		}

		return r;
	}
}