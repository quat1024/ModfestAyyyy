package agency.highlysuspect.mysterymodfest.dialogue;

import java.util.UUID;

/**
 * Someone or something who can speak in a conversation.
 */
public interface ConversationParty {
	VariableStore getVariableStore();
	UUID getConversationUuid();
	String getConversationName();
}
