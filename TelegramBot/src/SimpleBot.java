import org.omg.CORBA.PUBLIC_MEMBER;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class SimpleBot extends TelegramLongPollingBot {
    private String botName = "MFBOJ_Bot";
    private String token = "573542557:AAFlVoSerBfnSLsOJb2kPr-am1KMyucBcyE";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new SimpleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void onUpdateReceived(Update update) {
        Log log = new Log();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {

            String user_first_name = update.getMessage().getChat().getFirstName();
            String user_last_name = update.getMessage().getChat().getLastName();
            long user_id = update.getMessage().getChat().getId();
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            switch (message.getText()) {
                case "/start":
                    Layer1(message, "Добрый день" + "\n" + "Рады что вы посетили наш сайт");
                    Log.sendLog(user_first_name, user_last_name, user_id, message_text);
                    break;
                case "Заказать бота":
                    Layer1(message, "Каталог:");
                    Log.sendLog(user_first_name, user_last_name, user_id, message_text);
                    break;
                case "Назад":
                    Layer1(message, "Назад");
                    Log.sendLog(user_first_name, user_last_name, user_id, message_text);
                    break;
                case "Информация":
                    Layer2Info(message, "Выберите интересующий Вас раздел:");
                default:
                    Layer1(message, "К сожалению такие команды не предполагались" + "\n" + "Для более точного результа пользуйтесь навигационным меню");
                    Log.sendLog(user_first_name, user_last_name, user_id, message_text);
                    break;
            }
        }
    }

    public void Layer1(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Заказать бота");

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("Связаться с продавцом");

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add("Информация");

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);


        // устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        // пересылка сообщения
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void Layer2Info(Message message, String text) {
        SendPhoto sendPhoto = new SendPhoto();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Команда 1");
        keyboardFirstRow.add("Команда 2");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Команда 3");
        keyboardSecondRow.add("В главное меню");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        // пересылка сообщения
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        sendPhoto.setChatId(message.getChatId().toString());
        sendPhoto.setNewPhoto(new java.io.File("H:\\Telegram\\new.jpg"));
        try {
            sendMessage(sendMessage);
            sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}


