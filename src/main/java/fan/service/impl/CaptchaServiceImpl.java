package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.Producer;
import fan.captcha.CaptchaUtil;
import fan.core.util.MapUtil;
import fan.entity.ServerDO;
import fan.lang.Response;
import fan.redis.RedisUtil;
import fan.service.CaptchaService;
import fan.dao.ServerDAO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.Map;
import java.util.UUID;

/**
 * 验证码实现类
 *
 * @author Fan
 * @since 2023/7/6 16:24
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private Producer producer;

    @Resource
    private ServerDAO serverDAO;

    @Override
    public Response<Map<String, String>> getCaptcha(String server) {
        ServerDO serverDO = serverDAO.selectOne(new LambdaQueryWrapper<ServerDO>().eq(ServerDO::getCode, server)
                .eq(ServerDO::getFlag, "Y"));
        if (null == serverDO) {
            return Response.fail("服务器未注册", null);
        }

        // 生成验证码字符串
        String captcha = producer.createText();
        // 生成验证码图片
        String captchaImg = CaptchaUtil.getKaptcha(captcha);
        // 生成唯一标识
        String token = UUID.randomUUID().toString();
        // 存入 Redis, 有效期 120 秒
        RedisUtil.set(token, captcha, 120);
        return Response.success("获取验证码成功", MapUtil.builder("token", token)
                .put("captchaImg", captchaImg).build());
    }

    @Override
    public Response<Boolean> verifyCaptcha(String token, String captcha) {
        String verify = (String) RedisUtil.get(token);
        RedisUtil.del(token);

        if (null == verify) {
            return Response.fail("验证码已过期", false);
        } else if (!verify.equals(captcha)) {
            return Response.fail("验证码错误", false);
        }

        return Response.success("验证成功", true);
    }
}
