package com.douzone.mysite.action.user;

import com.douzone.mysite.action.main.MainAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class UserActionFactory extends ActionFactory {

	public Action getAction(String actionName) {
		switch(actionName) {
			case "joinform" : return new JoinFormAction();
			case "join" : return new JoinAction();
			case "joinsuccess" : return new JoinSuccessAction();
			case "loginform" : return new Loginform();
			case "login" : return new Login();
			case "logout" : return new LogoutAction();
			case "updateform" : return new updateformAction();
			case "update" : return new updateAction();
			
			default : return new MainAction();
		}
	}

}
