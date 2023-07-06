package fan.controller;

import fan.lang.Response;
import fan.service.CaptchaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 验证码 Controller
 *
 * @author Fan
 * @since 2023/7/6 16:20
 */
@RestController
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    @GetMapping("/getCaptcha")
    public Response getCaptcha() {
        return captchaService.getCaptcha();
    }

    @PostMapping("/verifyCaptcha")
    public Response verifyCaptcha(String captchaStr) {
        return captchaService.verifyCaptcha(captchaStr);
    }
}
