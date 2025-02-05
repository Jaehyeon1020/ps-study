//3425 고스택

import java.io.*;
import java.util.*;

public class Main {
	static final int LIMIT = 1000000000;
	
	public static void main(String[] args) throws IOException {
		ArrayList<ArrayList<String>> programs = new ArrayList<ArrayList<String>>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> program = new ArrayList<>();
		while (true) {
			String input = br.readLine();
			
			if (input.equals("QUIT")) break;
			if (input.equals("")) {
				programs.add(program);
				program = new ArrayList<String>();
				continue;
			}
			
			program.add(input);
		}
		br.close();
		
//		for (List<String> p: programs) {
//			System.out.println(p.toString());
//		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < programs.size(); i++) {
			List<String> p = programs.get(i);
			runProgram(p, bw);
			
			if (i != programs.size() - 1) {
				bw.write("\n");
				bw.flush();
			}
		}
	}
	
	static void runProgram(List<String> program, BufferedWriter bw) throws IOException {
		List<String> operations = new ArrayList<>();
		List<Long> inputs = new ArrayList<>();
		
		boolean isOperation = true;
		for (String p: program) {
			if (p.equals("END")) {
				isOperation = false;
				continue;
			}
			
			if (isOperation) operations.add(p);
			else inputs.add(Long.parseLong(p));
		}
		
//		System.out.println(operations);
//		System.out.println(inputs);
		
		for (int i = 1; i < inputs.size(); i++) {
			long input = inputs.get(i);
			runOperations(operations, input, bw);
		}
	}
	
	static void runOperations(List<String> operations, long input, BufferedWriter bw) throws IOException {
		ArrayDeque<Long> stack = new ArrayDeque<>();
		stack.addLast(input);
		
		for (String op: operations) {
			boolean isOpSucceed = runSingleOperation(op, stack);
			
			if (!isOpSucceed) {
				bw.write("ERROR\n");
				bw.flush();
				return;
			}
		}
		
		if (stack.size() == 1) {
			bw.write(Long.toString(stack.removeLast()) + "\n");
			bw.flush();
		} else {
			bw.write("ERROR\n");
			bw.flush();
		}
		
	}
	
	static boolean runSingleOperation(String operation, ArrayDeque<Long> stack) {
		String opHead = operation.substring(0, 3);
		long num1, num2, result;
		
//		System.out.println("Stack: " + stack);
		
		switch (opHead) {
		case "NUM":
			stack.addLast(Long.parseLong(operation.substring(4, operation.length())));
			return true;
		case "POP":
			if (stack.isEmpty()) return false;
			stack.removeLast();
			return true;
		case "INV":
			if (stack.isEmpty()) return false;
			num1 = stack.removeLast();
			if (Math.abs(num1) > LIMIT) return false;
			stack.addLast((-1) * num1);
			return true;
		case "DUP":
			if (stack.isEmpty()) return false;
			num1 = stack.peekLast();
			stack.addLast(num1);
			return true;
		case "SWP":
			if (stack.size() < 2) return false;
			num1 = stack.removeLast();
			num2 = stack.removeLast();
			stack.addLast(num1);
			stack.addLast(num2);
			return true;
		case "ADD":
			if (stack.size() < 2) return false;
			num1 = stack.removeLast();
			num2 = stack.removeLast();
			long sum = num1 + num2;
			if (Math.abs(sum) > LIMIT) return false;
			stack.addLast(sum);
			return true;
		case "SUB":
			if (stack.size() < 2) return false;
			num1 = stack.removeLast();
			num2 = stack.removeLast();
			long sub = num2 - num1;
			if (Math.abs(sub) > LIMIT) return false;
			stack.addLast(sub);
			return true;
		case "MUL":
			if (stack.size() < 2) return false;
			num1 = stack.removeLast();
			num2 = stack.removeLast();
			long mul = num1 * num2;
			if (Math.abs(mul) > LIMIT) return false;
			stack.addLast(mul);
			return true;
		case "DIV":
			if (stack.size() < 2) return false;
			num1 = stack.removeLast();
			num2 = stack.removeLast();
			if (num1 == 0) return false;
			
			result = Math.abs(num2) / Math.abs(num1);
			if (num1 * num2 < 0) stack.addLast((-1) * result);
			else stack.addLast(result);
			return true;
		case "MOD":
			if (stack.size() < 2) return false;
			num1 = stack.removeLast(); // 제수
			num2 = stack.removeLast(); // 피제수
			if (num1 == 0) return false;
			
			result = Math.abs(num2) % Math.abs(num1);
			if (num2 < 0) stack.addLast((-1) * result);
			else stack.addLast(result);
			return true;
		}
		return false;
	}
}