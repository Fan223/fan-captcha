package fan.service.impl;

import com.google.code.kaptcha.Producer;
import fan.core.exception.CommonException;
import fan.core.util.MapUtil;
import fan.lang.Response;
import fan.redis.RedisUtil;
import fan.service.CaptchaService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * 验证码实现类
 *
 * @author Fan
 * @since 2023/7/6 16:24
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    Log log = LogFactory.getLog(this.getClass());

    @Resource
    private Producer producer;

    @Override
    public Response getCaptcha() {
        // 生成验证码字符串
        String captchaStr = producer.createText();
        String captchaImg;
        try {
            // 生成验证码图片
            captchaImg = createKaptcha(captchaStr);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return Response.fail("获取验证码失败");
        }

        // 生成唯一标识
        String token = UUID.randomUUID().toString();
        RedisUtil.set(token, captchaStr, 120);
        return Response.success(MapUtil.builder().put("token", token).put("captchaStr", captchaStr)
                .put("captchaImg", captchaImg).build());
    }

    @Override
    public Response verifyCaptcha(String captchaStr) {
        return null;
    }

    public String createKaptcha(String captchaStr) {
        // 创建图片验证码
        BufferedImage bufferedImage = producer.createImage(captchaStr);

        // 创建字节数组
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 写入图片字节数组
        try {
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        } catch (IOException e) {
            throw new CommonException(e.getMessage());
        }

        // 转换为 base64, 生成图片验证码
        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
}
