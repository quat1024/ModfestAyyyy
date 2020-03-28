package agency.highlysuspect.mysterymodfest.util;

import agency.highlysuspect.mysterymodfest.dialogue.DialogueLineType;

public interface PlayerExt {
	DialogueLineType getCurrentDialogueLine();
	void setCurrentDialogueLine(DialogueLineType line);
}
