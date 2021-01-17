package com.github.jefffang.rule.condition;

import java.util.Optional;

import com.github.jefffang.rule.AbstractCondition;
import com.github.jefffang.rule.Fact;

import lombok.RequiredArgsConstructor;

//Generates a constructor with required arguments. Required arguments are final fields and fields with constraints such as @NonNull.
@RequiredArgsConstructor
public abstract class SingleFieldCondition extends AbstractCondition {
    private final String field;

    protected <T> Optional<T> fact(Fact fact) {
        return Optional.ofNullable(fact.getFact(field));
    }
}
