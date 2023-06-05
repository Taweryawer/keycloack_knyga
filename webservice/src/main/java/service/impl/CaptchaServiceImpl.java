package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import response.RecaptchaResponse;
import service.CaptchaService;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Value("${recaptcha.secretkey}")
    private String SECRET_KEY;
    private static final String GOOGLE_API =
        "https://www.google.com/recaptcha/api/siteverify";
    private Logger log = LogManager.getLogger(CaptchaServiceImpl.class);

    private ObjectMapper mapper;
    private OkHttpClient client;

    @Autowired
    public CaptchaServiceImpl(ObjectMapper mapper, OkHttpClient client) {
        this.mapper = mapper;
        this.client = client;
    }

    @Override
    public boolean confirmCaptcha(String token) {

        if (token == null) {
            return false;
        }

        RequestBody body = new FormBody.Builder().add("secret", SECRET_KEY)
            .add("response", token).build();

        Request request =
            new Request.Builder().url(GOOGLE_API).post(body).build();

        try {
            RecaptchaResponse response = mapper
                .readValue(client.newCall(request).execute().body().string(),
                    RecaptchaResponse.class);
            if (response.isSuccess()) {
                return true;
            } else {
                for (String error : response.getErrorCodes()) {
                    if (!error.endsWith("response")) {
                        log.error("Unexpected error during captcha verification, this may be very bad: " + error);
                    }
                }
                return false;
            }
        } catch (Exception e) {
            log.error("Error during captcha verification", e);
            return false;
        }
    }
}
