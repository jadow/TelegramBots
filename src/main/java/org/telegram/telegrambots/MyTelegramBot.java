package org.telegram.telegrambots;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;

public class MyTelegramBot extends TelegramLongPollingBot {
	
	    private static final String LOGTAG = "MAIN";
        
	    @Override
        public String getBotUsername() {
	    	return MyConfigs.BOT_USERNAME;
        }

        @Override
        public String getBotToken() {
        	return MyConfigs.BOT_TOKEN;
        }
        
        @Override
        public void onUpdateReceived(Update update) {
        	if(update.hasMessage())
        	{
        		Message message = update.getMessage();
        		SendMessage sendMessageRequest = new SendMessage();
        		sendMessageRequest.setChatId(message.getChatId().toString()); //who should get from the message the sender that sent it.
                sendMessageRequest.setText("you said: " + message.getText());
                try {
                    sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                } catch (TelegramApiException e) {
                //do some error handling
                }
        	}
        }

        
        public static void main(String[] args) {

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(new MyTelegramBot());
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }//end catch()
        }//end main()
}

