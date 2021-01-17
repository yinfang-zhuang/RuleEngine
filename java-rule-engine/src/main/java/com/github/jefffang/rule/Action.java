package com.github.jefffang.rule;

//http://tutorials.jenkov.com/java-functional-programming/functional-interfaces.html
public interface Action<T extends Fact> {
    void doAction(T fact);
}
