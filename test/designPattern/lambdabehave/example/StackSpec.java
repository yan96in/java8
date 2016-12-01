package designPattern.lambdabehave.example;

import java.util.Stack;

import static designPattern.lambdabehave.Lets.describe;

public class StackSpec {{//注意这里的双括号，其实是一个匿名构造函数，可以执行任意的java代码块，所以这等价于一个完整的构造函数

    describe("a stack", it -> {

        it.should("be empty when created", expect -> {
            expect.that(new Stack()).isEmpty();
        });

        it.should("push new elements onto the top of the stack", expect -> {
            Stack<Integer> stack = new Stack<>();
            stack.push(1);

            expect.that(stack.get(0)).isEqualTo(1);
        });

        it.should("pop the last element pushed onto the stack", expect -> {
            Stack<Integer> stack = new Stack<>();
            stack.push(2);
            stack.push(1);

            expect.that(stack.pop()).isEqualTo(2);
        });
        
    });

}}
