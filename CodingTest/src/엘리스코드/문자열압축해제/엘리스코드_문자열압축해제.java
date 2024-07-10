package 엘리스코드.문자열압축해제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 엘리스코드_문자열압축해제 {

    static String line;
    static Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();

        for (int i = line.length() - 1; i >= 0; i--) {
            String elem = String.valueOf(line.charAt(i));

            if (!elem.equals("(")) {
                stack.add(elem);
            } else {
                StringBuilder plusElem = new StringBuilder();
                while (!stack.peek().equals(")")) {
                    plusElem.append(stack.pop());
                }

                if(stack.peek().equals(")")){
                    stack.pop();
                    int loop = Integer.parseInt(String.valueOf(line.charAt(--i)));
                    StringBuilder multipleElem = new StringBuilder();
                    for(int j=0;j<loop;j++){
                        multipleElem.append(plusElem);
                    }

                    stack.add(multipleElem.toString());
                }
            }
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }

        System.out.println(result.length());
    }
}
