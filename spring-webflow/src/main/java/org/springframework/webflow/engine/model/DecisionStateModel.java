/*
 * Copyright 2004-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.webflow.engine.model;

import java.util.LinkedList;

import org.springframework.util.ObjectUtils;

/**
 * Model support for decision states.
 * <p>
 * Evaluates one or more expressions to decide what state to transition to next. Intended to be used as an idempotent
 * 'navigation' or 'routing' state.
 * <p>
 * A decision state is a transitionable state. A decision state transition can be triggered by evaluating a boolean
 * expression against the flow execution request context. To define transition expressions, use the 'if' element.
 * 
 * @author Scott Andrews
 */
public class DecisionStateModel extends AbstractStateModel {
	private LinkedList ifs;
	private LinkedList onExitActions;

	/**
	 * Create a decision state model
	 * @param id the state identifier
	 */
	public DecisionStateModel(String id) {
		setId(id);
	}

	/**
	 * Merge properties
	 * @param model the decision state to merge into this state
	 */
	public void merge(Model model) {
		DecisionStateModel state = (DecisionStateModel) model;
		setAttributes(merge(getAttributes(), state.getAttributes()));
		setSecured((SecuredModel) merge(getSecured(), state.getSecured()));
		setOnEntryActions(merge(getOnEntryActions(), state.getOnEntryActions(), false));
		setExceptionHandlers(merge(getExceptionHandlers(), state.getExceptionHandlers()));
		setIfs(merge(getIfs(), state.getIfs()));
		setOnExitActions(merge(getOnExitActions(), state.getOnExitActions(), false));
	}

	/**
	 * Tests if the model is able to be merged with this decision state
	 * @param model the model to test
	 */
	public boolean isMergeableWith(Model model) {
		if (!(model instanceof DecisionStateModel)) {
			return false;
		}
		DecisionStateModel state = (DecisionStateModel) model;
		return ObjectUtils.nullSafeEquals(getId(), state.getId());
	}

	/**
	 * @return the ifs
	 */
	public LinkedList getIfs() {
		return ifs;
	}

	/**
	 * @param ifs the ifs to set
	 */
	public void setIfs(LinkedList ifs) {
		this.ifs = ifs;
	}

	/**
	 * @param conditional the if to add
	 */
	public void addIf(IfModel conditional) {
		if (conditional == null) {
			return;
		}
		if (ifs == null) {
			ifs = new LinkedList();
		}
		ifs.add(conditional);
	}

	/**
	 * @param ifs the ifs to add
	 */
	public void addIf(LinkedList ifs) {
		if (ifs == null || ifs.isEmpty()) {
			return;
		}
		if (this.ifs == null) {
			this.ifs = new LinkedList();
		}
		this.ifs.addAll(ifs);
	}

	/**
	 * @return the on exit actions
	 */
	public LinkedList getOnExitActions() {
		return onExitActions;
	}

	/**
	 * @param onExitActions the on exit actions to set
	 */
	public void setOnExitActions(LinkedList onExitActions) {
		this.onExitActions = onExitActions;
	}

	/**
	 * @param onExitAction the on exit action to add
	 */
	public void addOnExitAction(AbstractActionModel onExitAction) {
		if (onExitAction == null) {
			return;
		}
		if (onExitActions == null) {
			onExitActions = new LinkedList();
		}
		onExitActions.add(onExitAction);
	}

	/**
	 * @param onExitActions the on exit actions to add
	 */
	public void addOnExitActions(LinkedList onExitActions) {
		if (onExitActions == null || onExitActions.isEmpty()) {
			return;
		}
		if (this.onExitActions == null) {
			this.onExitActions = new LinkedList();
		}
		this.onExitActions.addAll(onExitActions);
	}
}