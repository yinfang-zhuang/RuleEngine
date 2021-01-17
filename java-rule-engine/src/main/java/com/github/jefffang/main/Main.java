package com.github.jefffang.main;


import java.util.*;

import com.github.jefffang.rule.*;
import com.github.jefffang.rule.condition.*;
import com.github.jefffang.rule.engine.*;
import com.github.jefffang.rule.engine.impl.NativeRuleEngineImpl;
import com.github.jefffang.rule.fact.*;


public class Main {
	public static void main(String[] args)
	{
		Condition male = new SimpleCondition<>("gender", "male");
	    Condition female = new SimpleCondition<>("gender", "female");

	    Condition adult = new RangeCondition<Integer>("age", 18, 100);
	    Condition notAdult = NotCondition.reverse(adult);

	    Condition maleAdult = ConditionGroup.all(male, adult);
	    Condition femaleAdult = ConditionGroup.all(female, adult);
	    Condition maleNotAdult = ConditionGroup.all(male, notAdult);
	    Condition adultMaleOrFemale = ConditionGroup.all(ConditionGroup.any(male, female), adult);
	    //http://tutorials.jenkov.com/java-functional-programming/functional-interfaces.html
	    Action<Fact> print = f -> System.out.println("rule fired");
	    Action<Fact> printprint = ActionChain.all(Arrays.asList(print, print));
	    
	    Rule<Fact> printWhenMaleNotAdult = new BaseRule<>()
	            .setName("print when male not adult")
	            .setPriority(1)
	            .setExclusive(false)
	            .given(maleNotAdult)
	            .then(printprint);

	    Rule<Fact> printForFemaleAdult = new BaseRule<>()
	            .setName("print for male or female adult")
	            .setPriority(2)
	            .setExclusive(false)
	            .given(adultMaleOrFemale)
	            .then(print);
	    
	    RuleEngine ruleEngine = new NativeRuleEngineImpl();

	    List<KVFactPair> facts = new ArrayList<>();
	    //facts.add(new KVFactPair("gender", "male"));
	    facts.add(new KVFactPair("gender", "male"));
	    facts.add(new KVFactPair("age", 20));

	    Fact fact = new DefaultKVFact(new KVFactPairs(facts));
	    ruleEngine.runRules(Arrays.asList(printWhenMaleNotAdult, printForFemaleAdult), fact);
	}
}
