package com.msb.controller;

import com.google.code.kaptcha.Producer;
import com.msb.entity.CurrentUser;
import com.msb.entity.LoginUser;
import com.msb.entity.Result;
import com.msb.entity.User;
import com.msb.service.UserService;
import com.msb.utils.DigestUtil;
import com.msb.utils.TokenUtils;
import com.msb.utils.WarehouseConstants;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.msb.utils.WarehouseConstants.USER_STATE_NOT_PASS;

/**
 * 用户登录
 */
@RestController
public class LoginController {
@Autowired
private UserService userService;
    //注入DefaultKaptcha的bean对象生成一个验证码
    @Resource(name = "captchaProducer")
    private Producer producer;
    //注入redis模板
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private TokenUtils tokenUtils;
    /**
     * 生成验证码
     * @param response
     */
    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response) {
        ServletOutputStream out=null;
        try {
            //生成验证码图片的文字
            String text = producer.createText();
            //生成文本对应的照片
            BufferedImage image = producer.createImage(text);
            //将验证码的文本 作为见保存到redis 中设置键的过期时间
            redisTemplate.opsForValue().set(text,"",60*3, TimeUnit.SECONDS);
        /*
               将验证码照片相应给前端
         */
            //设置响应
            response.setContentType("image/jpeg");
            //将验证码图片写给前端
            out = response.getOutputStream();
            //使用响应对象的字节输出流写入验证码图片
            ImageIO.write(image,"jpg",out);
            //刷新
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //关流
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 登录的url接口
     * @RequestBody 注解的作用 将前端传过来的值接收并封装成json对象
     * 返回的result 对象 --
     * @param loginUser
     * @return
     */
    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser){
        //先判断表单中有没有输数据
        if (loginUser.getUserCode()== null || loginUser.getUserCode() ==""){
            return Result.err(Result.CODE_ERR_BUSINESS,"用户账号不能为空");
        }

        if (loginUser.getUserPwd()== null || loginUser.getUserPwd()==""){
            return Result.err(Result.CODE_ERR_BUSINESS,"用户密码不能为空");
        }

        System.out.println("验证码"+loginUser.getVerificationCode());
        if (loginUser.getVerificationCode()== null ||loginUser.getVerificationCode()==""){
            return Result.err(Result.CODE_ERR_BUSINESS,"验证码不能为空");
        }
        //通过用户账号查询用户对象
        User user = userService.queryUserByCode(loginUser.getUserCode());
        if (user==null){
            return Result.err(Result.CODE_ERR_BUSINESS,"用户对象不存在");
        }
        //获取表单中的密码
        String userPwd = loginUser.getUserPwd();
        //将密码加密后进行比较
        userPwd= DigestUtil.hmacSign(userPwd);
        if (!user.getUserPwd().equals(userPwd)){
            return Result.err(Result.CODE_ERR_BUSINESS,"用户密码不正确");
        }
        //获取客户输入的验证码
        String verificationCode = loginUser.getVerificationCode();
        //判断redis中是否含有redis
        Boolean aBoolean = redisTemplate.hasKey(verificationCode);
        if (!aBoolean){
            return Result.err(Result.CODE_ERR_BUSINESS,"验证码不正确");
        }
        //判断用户是否配审核过  0 未审核   1 已经审核
        if (user.getUserState().equals(USER_STATE_NOT_PASS)) {
            return Result.err(Result.CODE_ERR_BUSINESS, "用户未审核");
        }
        //生成jwt token 存储到redis 中
        CurrentUser currentUser = new CurrentUser(user.getUserId(),user.getUserCode(),user.getUserName());
        String token = tokenUtils.loginSign(currentUser, userPwd);
        //将生成的token 相应给客户端
        return  Result.ok("登陆成功",token);
    }

}
