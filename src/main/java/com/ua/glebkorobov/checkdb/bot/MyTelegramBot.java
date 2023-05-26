package com.ua.glebkorobov.checkdb.bot;

import com.ua.glebkorobov.checkdb.config.BotConfig;
import com.ua.glebkorobov.checkdb.model.Client;
import com.ua.glebkorobov.checkdb.model.Procedures;
import com.ua.glebkorobov.checkdb.repository.VisitProceduresRepository;
import com.ua.glebkorobov.checkdb.service.ClientService;
import com.ua.glebkorobov.checkdb.model.Commands;
import com.ua.glebkorobov.checkdb.service.ProcedureService;
import com.ua.glebkorobov.checkdb.service.VisitProceduresService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static com.ua.glebkorobov.checkdb.model.Commands.*;


@Component
@Log4j2
public class MyTelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProcedureService procedureService;

    @Autowired
    private VisitProceduresService visitProceduresService;

    private Commands statusCommand;

    public MyTelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/add_client", "Add client"));
        listOfCommands.add(new BotCommand("/add_procedure", "Add procedure"));
        listOfCommands.add(new BotCommand("/add_visits", "Add client visit"));
        listOfCommands.add(new BotCommand("/pick_client", "Pick client"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException ex) {
            log.warn(ex);
        }
    }
//TODO add visits
    /**
     * @see Commands enams for switch statement
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String message = update.getMessage().getText();

            if (message.charAt(0) == '/') {
                switch (message) {
                    case "/add_client": {
                        statusCommand = ADD_CLIENT;
                        sendMessage(chatId, "For adding client write\nPattern: Phone, Name");
                        break;
                    }
                    case "/add_procedure":{
                        statusCommand = ADD_PROCEDURE;
                        sendMessage(chatId, "For adding procedure write\nPattern: Name, Price");
                        break;
                    }
                    default:{
                        break;
                    }
                }
                return;
            }

            if (statusCommand == ADD_CLIENT){
                String[] arr = message.split(",");
                Client client = new Client(arr[0], arr[1]);
                sendMessage(chatId, clientService.addClient(client));
                statusCommand = NONE;
            } else if (statusCommand == ADD_PROCEDURE) {
                String[] arr = message.split(",");
                Procedures procedures = new Procedures(arr[0], Long.parseLong(arr[1].trim()));
                sendMessage(chatId, procedureService.addProcedure(procedures));
                statusCommand = NONE;
            }
        }
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.warn(e);
        }
    }


    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
