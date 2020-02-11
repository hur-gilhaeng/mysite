package com.douzone.mysite.action.board;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		if(actionName==null) {
			return new BoardAction();
		}
		
		switch(actionName) {
			case "write": return new BoardWriteAction();
			case "insert": return new BoardInsertAction();
			case "delete": return new BoardAction();

			default : return new BoardAction();
		}
	}

}
