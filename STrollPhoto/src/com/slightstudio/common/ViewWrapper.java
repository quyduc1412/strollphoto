package com.slightstudio.common;

import android.view.View;

public class ViewWrapper {
	private View view;
	private IActionVisibility action;
	public ViewWrapper(View view) {
		super();
		this.view = view;
	}
	
	public ViewWrapper(View view, IActionVisibility action) {
		super();
		this.view = view;
		this.action = action;
	}

	public IActionVisibility getAction() {
		return action;
	}

	public void setAction(IActionVisibility action) {
		this.action = action;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setVisibility(int visibility){
		view.setVisibility(visibility);
		if(action != null){
			action.setVisibility(visibility,this.view);
		}
	}
	public interface IActionVisibility{
		public void setVisibility(int visibility,View view);
	}
}
