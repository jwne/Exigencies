package com.teaminfinity.exigencies.objects.command;

import com.teaminfinity.exigencies.enums.Cmd;

public class ExigenciesCommand {

	private final String alias;
	private final Cmd commandExample;
	
	public ExigenciesCommand(String alias, Cmd commandExample)
	{
		this.alias = alias;
		this.commandExample = commandExample;
	}

	public String getAlias() {
		return alias;
	}

	public Cmd getCommandExample() {
		return commandExample;
	}

}
