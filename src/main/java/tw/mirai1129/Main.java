package tw.mirai1129;

import org.json.JSONObject;
import tw.mirai1129.OpenAI.ChatGptAPI;

import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties prop = PropertiesUtils.loadProperties("chatgptapi.properties");
        ChatGptAPI chatgptApi = new ChatGptAPI(prop);
        String prompt = "good morning what should I eat?";
        String requestBody = String.format(
                "{ \"prompt\": \"%s\"," +
                        " \"temperature\": 0.5," +
                        " \"max_tokens\": 600," +
                        " \"top_p\": 1," +
                        " \"frequency_penalty\": 0," +
                        " \"presence_penalty\": 0 }",
                prompt
        );
        JSONObject jsonObject = chatgptApi.send(requestBody);
        String ans = chatgptApi.getGptResponseText(jsonObject);
        System.out.println(ans);
    }
}
