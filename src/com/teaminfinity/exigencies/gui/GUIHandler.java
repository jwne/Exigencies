package com.teaminfinity.exigencies.gui;

public abstract class GUIHandler {

	private transient static ExigenciesGUI[] guis = new ExigenciesGUI[]
			{
				new WhoisGUI(),
				new LagGUI(),
				new AbuseGUI(),
				new ParticleEffectGUI(),
				new CheckGUI(),
				new DosGUI()
			}
	;
	
	public static DosGUI getDos()
	{
		return (DosGUI) guis[5];
	}
	
	public static CheckGUI getCheck()
	{
		return (CheckGUI) guis[4];
	}
	
	public static ParticleEffectGUI getParticleEffect()
	{
		return (ParticleEffectGUI) guis[3];
	}
	
	public static AbuseGUI getAbuse()
	{
		return (AbuseGUI) guis[2];
	}
	
	public static LagGUI getLag()
	{
		return (LagGUI) guis[1];
	}
	
	public static WhoisGUI getWhois()
	{
		return (WhoisGUI) guis[0];
	}
	
	public static ExigenciesGUI[] getAll()
	{
		return guis;
	}
	
}
