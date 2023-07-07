package fan.controller;

import fan.lang.Response;
import fan.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 验证码 Controller
 *
 * @author Fan
 * @since 2023/7/6 16:20
 */
@RestController
@Slf4j
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    @GetMapping("/getCaptcha")
    public Response<Map<String, String>> getCaptcha(String server) {
        try {
            return captchaService.getCaptcha(server);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.fail("获取验证码失败", null);
        }
    }

    @PostMapping("/verifyCaptcha")
    public Response<Boolean> verifyCaptcha(String token, String captcha) {
        try {
            return captchaService.verifyCaptcha(token, captcha);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.fail("验证失败", false);
        }
    }
}
