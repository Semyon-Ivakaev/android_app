package com.example.vksearchapp.utils;
/*
В методе generateURL мы сперва формируем URI а потом переводим его в URL
потому что Uri это библиотека андроида, а URL от джавы
 */
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String TOKEN = "fbe75828fbe75828fbe75828d6fb96f619ffbe7fbe75828a4b79e7cd2e4437594fbf53e";

    public static URL generateURL(String userId) {
        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()            // позволяет начать конструировать URI
                .appendQueryParameter(PARAM_USER_ID, userId) // позволяет добавлять параметры к запросу и с ним то, что хотим искать
                .appendQueryParameter(PARAM_VERSION, "5.89")
                .appendQueryParameter(ACCESS_TOKEN, TOKEN)
                .build(); // сообщаем, что закончили конструировать URI

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        // Сперва открываем коннекшен хттп
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // (HttpURLConnection) даункастинг
        try {
            //считываем входной поток с помощью сканнера
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            // эта штука разделяет строку по определенным сиволам (по умолчанию пробел)
            // \A будет делить строку на под строки
            scanner.useDelimiter("\\A"); // например Sss dd t, по пробелу может разделять и потом выведет в каждую строку

            boolean hasInput = scanner.hasNext(); // проверяем, что потом не пустой

            if (hasInput) {
                return scanner.next(); // вернет всю строку
            } else {
                return null;
            }
        }
        finally {
                urlConnection.disconnect(); // завершаем соединение, тут подсветит ошибкой, поэтому оборачиваем выше код в трай
            }
    }
}
