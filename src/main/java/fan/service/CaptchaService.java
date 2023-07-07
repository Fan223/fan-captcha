package fan.service;

import fan.lang.Response;

import java.util.Map;

/**
 * 验证码接口
 *
 * @author Fan
 * @since 2023/7/6 16:22
 */
public interface CaptchaService {

    /**
     * 获取验证码
     *
     * @param server 服务
     * @return {@link Response<Map<String,String>>}
     * @author Fan
     * @since 2023/7/6 16:23
     */
    Response<Map<String, String>> getCaptcha(String server);

    /**
     * 校验验证码
     *
     * @param token   token
     * @param captcha 验证码
     * @return {@link Response<Boolean>}
     * @author Fan
     * @since 2023/7/6 17:18
     */
    Response<Boolean> verifyCaptcha(String token, String captcha);
}
