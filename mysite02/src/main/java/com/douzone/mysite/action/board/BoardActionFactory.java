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
			case "writeform": return new BoardWriteFormAction();
			case "write": return new BoardWriteAction();
			case "delete": return new BoardAction();
			case "view": return new BoardAction();
			case "modifyform": return new BoardAction();
			case "modify": return new BoardAction();

			default : return new BoardAction();
		}
	}

}
