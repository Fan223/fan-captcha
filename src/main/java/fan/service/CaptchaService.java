package fan.service;

import fan.lang.Response;

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
     * @return {@link Response}
     * @author Fan
     * @since 2023/7/6 16:23
     */
    Response getCaptcha();

    /**
     * 校验验证码
     *
     * @param captchaStr 验证码字符串
     * @return {@link Response}
     * @author Fan
     * @since 2023/7/6 17:18
     */
    Response verifyCaptcha(String captchaStr);
}
