package com.github.jefffang.rule.fact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class KVFactPair {
	@Getter @Setter private String key = "";
	@Getter @Setter private Object value = "";
}
