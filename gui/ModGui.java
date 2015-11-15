package net.playmcm.qwertysam.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.playmcm.qwertysam.ModMain;
import net.playmcm.qwertysam.io.OptionKey;
import net.playmcm.qwertysam.messages.api.MessageType;
import net.playmcm.qwertysam.messages.api.URL;
import net.playmcm.qwertysam.util.UrlOpener;

public class ModGui extends GuiScreen
{
	/** The pixels between the GUI and the top of the screen **/
	private static final int spaceFromTop = 36;

	/** The horizontal spacing between the buttons **/
	public static final int xSpacing = 3;

	/** The vertical spacing between the buttons **/
	public static final int ySpacing = 26;

	/** The width of the buttons **/
	public static final int smallButtonWidth = 120;

	/** The width of the buttons **/
	public static final int linkButtonWidth = smallButtonWidth - 20 - xSpacing;

	/** Tells whether the player just entered the GUI or not. */
	private boolean justEntered;

	/** Tells Minecraft it's ready to exit the gui as soon as the player lets go of ESC. */
	private boolean waitForRelease;
	
	private ModMain mod;

	public ModGui(ModMain mod)
	{
		super();
		this.mod = mod;
	}

	@Override
	public void initGui()
	{
		justEntered = true;
		waitForRelease = false;

		this.buttonList.clear();

		// this.buttonList.add(new GuiIconButton(47, this.width / 2 + smallButtonWidth + (xSpacing * 2), spaceFromTop + 20, 20, 0, 20, 20));

		// How to become mod
		this.buttonList.add(new GuiButton(0, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop, linkButtonWidth, 20, "Getting Staff"));
		this.buttonList.add(new GuiIconButton(1, this.width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop, smallButtonWidth, 0));

		// Rules Link
		this.buttonList.add(new GuiButton(2, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 1), linkButtonWidth, 20, "Rules"));
		this.buttonList.add(new GuiIconButton(3, this.width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 1), smallButtonWidth, 0));

		// Reports Link
		this.buttonList.add(new GuiButton(4, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 2), linkButtonWidth, 20, "Reporting"));
		this.buttonList.add(new GuiIconButton(5, this.width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 2), smallButtonWidth, 0));

		// Ban Appeals Link
		this.buttonList.add(new GuiButton(10, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 3), linkButtonWidth, 20, "Ban Appeals"));
		this.buttonList.add(new GuiIconButton(11, this.width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 3), smallButtonWidth, 0));

		// Vote Link
		this.buttonList.add(new GuiButton(6, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 4), linkButtonWidth, 20, "Voting"));
		this.buttonList.add(new GuiIconButton(7, this.width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 4), smallButtonWidth, 0));

		// Donation Link
		this.buttonList.add(new GuiButton(8, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 5), linkButtonWidth, 20, "Donating"));
		this.buttonList.add(new GuiIconButton(9, this.width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 5), smallButtonWidth, 0));

		// Forum Link
		this.buttonList.add(new GuiButton(25, this.width / 2 - smallButtonWidth - xSpacing, spaceFromTop + (ySpacing * 6), linkButtonWidth, 20, "Forum Link"));
		this.buttonList.add(new GuiIconButton(26, this.width / 2 - smallButtonWidth + linkButtonWidth, spaceFromTop + (ySpacing * 6), smallButtonWidth, 0));

		// Explain Teaming
		this.buttonList.add(new GuiButton(12, this.width / 2 + xSpacing, spaceFromTop, smallButtonWidth, 20, "Explain Teaming"));

		// Explain Arguing
		this.buttonList.add(new GuiButton(13, this.width / 2 + xSpacing, spaceFromTop + (ySpacing * 1), smallButtonWidth, 20, "Explain Arguing"));

		// Explain RDM
		this.buttonList.add(new GuiButton(14, this.width / 2 + xSpacing, spaceFromTop + (ySpacing * 2), smallButtonWidth, 20, "Explain RDM"));

		// Custom 1
		this.buttonList.add(new GuiButton(15, this.width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 3), linkButtonWidth, 20, mod.getOptions().getOption(OptionKey.customOneTitle).asString()));
		this.buttonList.add(new GuiIconButton(16, this.width / 2 + xSpacing, spaceFromTop + (ySpacing * 3), 0, 0, 20, 20));

		// Custom 2
		this.buttonList.add(new GuiButton(17, this.width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 4), linkButtonWidth, 20, mod.getOptions().getOption(OptionKey.customTwoTitle).asString()));
		this.buttonList.add(new GuiIconButton(18, this.width / 2 + xSpacing, spaceFromTop + (ySpacing * 4), 0, 0, 20, 20));

		// Custom 3
		this.buttonList.add(new GuiButton(23, this.width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 5), linkButtonWidth, 20, mod.getOptions().getOption(OptionKey.customThreeTitle).asString()));
		this.buttonList.add(new GuiIconButton(24, this.width / 2 + xSpacing, spaceFromTop + (ySpacing * 5), 0, 0, 20, 20));

		// Custom 4
		this.buttonList.add(new GuiButton(27, this.width / 2 + (xSpacing * 2) + 20, spaceFromTop + (ySpacing * 6), linkButtonWidth, 20, mod.getOptions().getOption(OptionKey.customFourTitle).asString()));
		this.buttonList.add(new GuiIconButton(28, this.width / 2 + xSpacing, spaceFromTop + (ySpacing * 6), 0, 0, 20, 20));

		this.buttonList.add(new GuiIconButton(19, this.width / 2 + 148, (this.height / 2) - 40 - (xSpacing * 2), 80, 0, 20, 20));
		this.buttonList.add(new GuiIconButton(20, this.width / 2 + 148, (this.height / 2) - 20 - xSpacing, 60, 0, 20, 20));
		this.buttonList.add(new GuiIconButton(21, this.width / 2 + 148, (this.height / 2), 40, 0, 20, 20));
		this.buttonList.add(new GuiIconButton(22, this.width / 2 + 148, (this.height / 2) + 20 + (xSpacing * 1), 100, 0, 20, 20));
	}

	/**
	 * Fires when a button is clicked.
	 * 
	 * @param button the button that was clicked
	 */
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		switch (button.id)
		{
			case 0:
				mod.message().sendMessage(MessageType.BECOME_STAFF);
				exitGui();
				break;
			case 1:
				mod.message().sendMessage(MessageType.BECOME_STAFF, true);
				exitGui();
				break;
			case 2:
				mod.message().sendMessage(MessageType.RULES);
				exitGui();
				break;
			case 3:
				mod.message().sendMessage(MessageType.RULES, true);
				exitGui();
				break;
			case 4:
				mod.message().sendMessage(MessageType.REPORTS);
				exitGui();
				break;
			case 5:
				mod.message().sendMessage(MessageType.REPORTS, true);
				exitGui();
				break;
			case 6:
				mod.message().sendMessage(MessageType.VOTE);
				exitGui();
				break;
			case 7:
				mod.message().sendMessage(MessageType.VOTE, true);
				exitGui();
				break;
			case 8:
				mod.message().sendMessage(MessageType.DONATE);
				exitGui();
				break;
			case 9:
				mod.message().sendMessage(MessageType.DONATE, true);
				exitGui();
				break;
			case 10:
				mod.message().sendMessage(MessageType.BAN_APPEALS);
				exitGui();
				break;
			case 11:
				mod.message().sendMessage(MessageType.BAN_APPEALS, true);
				exitGui();
				break;
			case 12:
				mod.message().sendMessage(MessageType.EXPLAIN_TEAMING);
				exitGui();
				break;
			case 13:
				mod.message().sendMessage(MessageType.EXPLAIN_ARGUE);
				exitGui();
				break;
			case 14:
				mod.message().sendMessage(MessageType.EXPLAIN_RDM);
				exitGui();
				break;
			case 15:
				mod.message().sendMessage(MessageType.CUSTOM1);
				exitGui();
				break;
			case 16:
				this.mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), OptionKey.customOneTitle, OptionKey.customOne1, OptionKey.customOne2, OptionKey.customOne3));
				break;
			case 17:
				mod.message().sendMessage(MessageType.CUSTOM2);
				exitGui();
				break;
			case 18:
				this.mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), OptionKey.customTwoTitle, OptionKey.customTwo1, OptionKey.customTwo2, OptionKey.customTwo3));
				break;
			case 19:
				mod.message().sendDelayedMessage("/warn help", 0); // Sends the command that displays info on the /warn command usage
				exitGui();
				break;
			case 20:
				UrlOpener.openURL(URL.WEBSITE); // Forum Page
				exitGui();
				break;
			case 21:
				UrlOpener.openURL(URL.GUIDELINES); // Mod Guidelines Documentation page
				exitGui();
				break;
			case 22:
				UrlOpener.openURL(URL.TRELLO); // Bugs and Suggestions page
				exitGui();
				break;
			case 23:
				mod.message().sendMessage(MessageType.CUSTOM3);
				exitGui();
				break;
			case 24:
				this.mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), OptionKey.customThreeTitle, OptionKey.customThree1, OptionKey.customThree2, OptionKey.customThree3));
				break;
			case 25:
				mod.message().sendMessage(MessageType.FORUM);
				exitGui();
				break;
			case 26:
				mod.message().sendMessage(MessageType.FORUM, true);
				exitGui();
				break;
			case 27:
				mod.message().sendMessage(MessageType.CUSTOM4);
				exitGui();
				break;
			case 28:
				this.mc.displayGuiScreen(new GuiCustomEditor(this, mod.getOptions(), OptionKey.customFourTitle, OptionKey.customFour1, OptionKey.customFour2, OptionKey.customFour3));
				break;
		}
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	/**
	 * Exits the GUI.
	 */
	public void exitGui()
	{
		this.mc.displayGuiScreen((GuiScreen) null);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		// Waits for ESC to be released before exiting GUI, prevents opening of the in-game pause menu upon exit of GUI
		if ((!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) && waitForRelease)
		{
			this.exitGui();
		}
		
		if ((Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) && !this.justEntered)
		{
			waitForRelease = true;
		}
		else if (justEntered && !Keyboard.isKeyDown(Keyboard.KEY_GRAVE) && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) // Waits for the GRAVE key to be released before focusing on the TextField or allowing exiting through the GRAVE key being pressed.
		{
			justEntered = false;
		}

		this.drawCenteredString(this.fontRendererObj, "Mod Tools", this.width / 2, spaceFromTop - 16, 16777215);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
