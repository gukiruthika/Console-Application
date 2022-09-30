package Calculator;

import java.util.LinkedList;
import java.util.List;

public class Calculation {
	public void calculate(String problem) throws Exception {
		Expression ob1 = new Expression();
		Validation ob2 = new Validation();
		LinkedList<Object> terms = new LinkedList<Object>();
		if (ob2.check(problem)) {
			terms = ob1.toTerms(problem);
			double ans = solve(terms);
			if ((int) ans == ans)
				System.out.println((int) ans);
			else
				System.out.println(ans);
		} else {
			System.out.print("Invalid problem.. try again");
		}

	}

	Double solve(List<Object> terms) {
		int index, j;
		Double ans;
		List<Object> innerTerms = new LinkedList<Object>();
		while (terms.size() > 1) {

			if (terms.contains("(")) {
				index = terms.indexOf("(");
				j = terms.indexOf(")");
				innerTerms = terms.subList(index + 1, j + 1);
				if (innerTerms.contains("(")) {
					index = innerTerms.indexOf("(");
					j = innerTerms.indexOf(")");
					ans = solve(innerTerms.subList(index + 1, j));
					innerTerms.remove("(");
					innerTerms.remove(")");
				} else {
					ans = solve(terms.subList(index + 1, j));
					terms.remove("(");
					terms.remove(")");
				}

			} else if (terms.contains("cbrt")) {
				index = terms.indexOf("cbrt");
				ans = Math.cbrt(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("sqrt")) {
				index = terms.indexOf("sqrt");
				ans = Math.sqrt(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("log")) {
				index = terms.indexOf("log");
				ans = Math.log10(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("sin")) {
				index = terms.indexOf("sin");
				ans = Math.sin(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("cos")) {
				index = terms.indexOf("cos");
				ans = Math.cos(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("tan")) {
				index = terms.indexOf("tan");
				ans = Math.tan(getValue(index + 1, terms));
				toSimplify(terms, index + 1, 2, ans);
			} else if (terms.contains("%")) {
				index = terms.indexOf("%");
				ans = (getValue(index - 1, terms)) / 100;
				toSimplify(terms, index, 2, ans);
			} else if (terms.contains("!")) {
				index = terms.indexOf("!");
				ans = factorial(getValue(index - 1, terms));
				toSimplify(terms, index, 2, ans);
			} else if (terms.contains("^")) {
				index = terms.indexOf("^");
				ans = Math.pow(getValue(index - 1, terms), getValue(index + 1, terms));
				toSimplify(terms, index, 3, ans);
			} else if (terms.contains("/")) {
				index = terms.indexOf("/");
				ans = getValue(index - 1, terms) / getValue(index + 1, terms);
				toSimplify(terms, index, 3, ans);
			} else if (terms.contains("*")) {
				index = terms.indexOf("*");
				ans = getValue(index - 1, terms) * getValue(index + 1, terms);
				toSimplify(terms, index, 3, ans);
			} else if (terms.contains("-")) {
				index = terms.indexOf("-");
				ans = getValue(index - 1, terms) - getValue(index + 1, terms);
				toSimplify(terms, index, 3, ans);
			} else if (terms.contains("+")) {
				index = terms.indexOf("+");
				ans = getValue(index - 1, terms) + getValue(index + 1, terms);
				toSimplify(terms, index, 3, ans);
			}
		}
		return (Double) terms.get(0);

	}

	Double getValue(int index, List<Object> terms) {
		return Double.valueOf((terms.get(index)).toString());
	}

	Double factorial(double n) {
		double a = 1.0;
		while (n > 0) {
			a *= n;
			n--;
		}
		return a;
	}

	void toSimplify(List<Object> a, int i, int n, Double ans) {
		a.add(i - 1, ans);
		while (n-- > 0) {
			a.remove(i);
		}

	}
}
