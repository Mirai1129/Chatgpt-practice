package tw.mirai1129.OpenAI;


import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;

public class ChatGptAPI {
    String chatgptApiKey;
    String model;

    public ChatGptAPI(@NotNull Properties prop) {
        chatgptApiKey = prop.getProperty("CHATGPT_API_KEY");
        model = prop.getProperty("MODEL");
    }

    public JSONObject send(String jsonStr) throws IOException {
        String apiUrl = "https://api.openai.com/v1/engines/%s/completions".formatted(model);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(jsonStr, mediaType);
        Request request = new Request.Builder().url(apiUrl)
                .post(body)
                .addHeader("Authorization", "Bearer " + chatgptApiKey)
                .addHeader("Content-Type", "application/json")
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String responseBody = response.body().string();
        return new JSONObject(responseBody);
    }

    public String getGptResponseText(@NotNull JSONObject response) {
        JSONArray choicesArray = response.getJSONArray("choices");
        JSONObject firstChoice = choicesArray.getJSONObject(0);
        return firstChoice.getString("text");
    }
}
