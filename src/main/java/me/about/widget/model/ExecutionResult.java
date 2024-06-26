package me.about.widget.model;

import java.io.Serializable;
import java.util.Map;

import lombok.NonNull;

public interface ExecutionResult extends Serializable {

	 @NonNull Map<String, Object> getResults();
	
	 default Object getValue(String name) {
		if (name == null)
			return null;
		Map<String, Object> results = getResults();
        return results.get(name);
	}

	 default Object getValue() {
		Map<String, Object> results = getResults();
		if (results.isEmpty())
			return null;
		return results.entrySet().iterator().next().getValue();
	}
}
