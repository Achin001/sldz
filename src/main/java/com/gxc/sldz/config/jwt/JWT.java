package com.gxc.sldz.config.jwt;




import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Slf4j
@Component
public class JWT {

    /**
     * 加密秘钥 读取application.yml
     */
    @Value("${jwt.secret}")
    private String secret ;
    /**
     * 有效时间
     * 604800000 ms = 1周
     */
    @Value("${jwt.expire}")
    private long expire;
    /**
     * 用户凭证
     */
    @Value("${jwt.header}")
    private String header;



    /**
     * 生成Token签名
     *
     * @param userId 用户ID
     * @return 签名字符串
     * @author Achin
     */
    public String generateToken(long userId) {
        log.info("header=" + getHeader() + ", expire=" + getExpire() + ", secret=" + getSecret());
        Date nowDate = new Date();
// 过期时间
        Date expireDate = new Date(nowDate.getTime() + expire);
        return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(String.valueOf(userId)).setIssuedAt(nowDate)
                .setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, getSecret()).compact();
// 注意: JDK版本高于1.8, 缺少 javax.xml.bind.DatatypeConverter jar包,编译出错
    }

    /**
     * 获取签名信息
     * @param token
     * @author Achin
     */
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * 判断Token是否过期
     * @param expiration
     * @return true 过期
     * @author Achin
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

}
