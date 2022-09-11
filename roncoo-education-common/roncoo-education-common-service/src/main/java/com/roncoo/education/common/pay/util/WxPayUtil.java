package com.roncoo.education.common.pay.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.roncoo.education.common.core.base.BaseException;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;

/**
 * 微信支付工具类
 *
 * @author LYQ
 */
@Slf4j
public class WxPayUtil {

    private static final String DATE_FORMAT = "YYYY-mm-ddTHH:mm:ss+TIMEZONE";

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100L);

    /**
     * 获取HTTP客户端
     *
     * @param wxPayConfig 微信支付配置
     * @return HTTP客户端
     */
    private static CloseableHttpClient getCloseableHttpClient(WxPayConfig wxPayConfig) {
        try {
            // 加载商户私钥（privateKey：私钥字符串）
            PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(new ByteArrayInputStream(wxPayConfig.getWxPayWxMchPrivateKey().getBytes(StandardCharsets.UTF_8)));
            X509Certificate certificate = PemUtil.loadCertificate(new ByteArrayInputStream(wxPayConfig.getWxPayMchPrivateCert().getBytes(StandardCharsets.UTF_8)));
            String certSerialNo = certificate.getSerialNumber().toString(16).toUpperCase();

            // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
            CertificatesManager certificatesManager = CertificatesManager.getInstance();
            certificatesManager.putMerchant(wxPayConfig.getWxPayMchId(), new WechatPay2Credentials(wxPayConfig.getWxPayMchId(), new PrivateKeySigner(certSerialNo, merchantPrivateKey)), wxPayConfig.getWxPayApiV3Key().getBytes(StandardCharsets.UTF_8));
            Verifier verifier = certificatesManager.getVerifier(wxPayConfig.getWxPayMchId());

            // 初始化httpClient
            return WechatPayHttpClientBuilder.create()
                    .withMerchant(wxPayConfig.getWxPayMchId(), certSerialNo, merchantPrivateKey)
                    .withValidator(new WechatPay2Validator(verifier)).build();
        } catch (Exception e) {
            log.error("微信支付--初始化请求客户端失败！", e);
            throw new BaseException("wechat pay init http client error");
        }
    }

    /**
     * 下载微信平台证书
     * <p>
     * 在启用新的平台证书前，微信支付会提前24小时把新证书加入到平台证书列表中
     * 接口的频率限制: 单个商户号1000 次/s
     *
     * @param wxPayConfig 微信配置
     * @return 证书内容
     * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/wechatpay5_1.shtml">平台证书</a>
     */
    public static String downloadCert(WxPayConfig wxPayConfig) {
        try {
            CloseableHttpClient httpClient = getCloseableHttpClient(wxPayConfig);

            HttpGet httpGet = new HttpGet("https://api.mch.weixin.qq.com/v3/certificates");
            httpGet.addHeader(HttpHeaders.ACCEPT, "application/json");
            // 后面跟使用Apache HttpClient一样
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // step4：返回响应信息
            String result = EntityUtils.toString(response.getEntity());

            JSONObject jsonObj = JSONUtil.parseObj(result);
            JSONObject contentObj = jsonObj.getJSONArray("data").getJSONObject(0);
            String ciphertext = contentObj.getJSONObject("encrypt_certificate").getStr("ciphertext");
            String associatedData = contentObj.getJSONObject("encrypt_certificate").getStr("associated_data");
            String nonce = contentObj.getJSONObject("encrypt_certificate").getStr("nonce");

            AesUtil aesUtil = new AesUtil(wxPayConfig.getWxPayApiV3Key().getBytes(StandardCharsets.UTF_8));
            return aesUtil.decryptToString(associatedData.getBytes(StandardCharsets.UTF_8), nonce.getBytes(StandardCharsets.UTF_8), ciphertext);
        } catch (Exception e) {
            log.error("微信支付--获取平台证书列表失败！e");
        }
        return null;
    }

    public static String sign(String message, PrivateKey privateKey) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(message.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(sign.sign());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持SHA256withRSA", e);
        } catch (SignatureException e) {
            throw new RuntimeException("签名计算失败", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("无效的私钥", e);
        }
    }

    /**
     * 初始化微信支付服务
     *
     * @param wxPayConfig 微信支付配置
     * @return 微信支付服务
     */
    public static WxPayService initService(WxPayConfig wxPayConfig) {
        com.github.binarywang.wxpay.config.WxPayConfig payConfig = new com.github.binarywang.wxpay.config.WxPayConfig();
        payConfig.setAppId(wxPayConfig.getWxPayAppId());
        payConfig.setMchId(wxPayConfig.getWxPayMchId());
        payConfig.setPrivateCertContent(wxPayConfig.getWxPayMchPrivateCert().getBytes(StandardCharsets.UTF_8));
        payConfig.setPrivateKeyContent(wxPayConfig.getWxPayWxMchPrivateKey().getBytes(StandardCharsets.UTF_8));
        payConfig.setApiV3Key(wxPayConfig.getWxPayApiV3Key());
        payConfig.setSubAppId(wxPayConfig.getSubAppId());
        payConfig.setSubMchId(wxPayConfig.getSubMchId());

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

    /**
     * 交易结束时间格式化
     *
     * @param timeExpire 交易结束时间
     * @return 交易结束时间
     */
    public static String timeExpireFormat(Date timeExpire) {
        return DateUtil.format(timeExpire, DatePattern.UTC_WITH_XXX_OFFSET_PATTERN);
    }

    /**
     * 时间字符串转解析成时间
     *
     * @param dateStr 时间字符串
     * @return 时间
     */
    public static Date dateParse(String dateStr) {
        return DateUtil.parse(dateStr, DATE_FORMAT);
    }

    /**
     * 金额元转成分
     *
     * @param amount 金额
     * @return 分
     */
    public static Integer yuanToCent(BigDecimal amount) {
        return amount.multiply(HUNDRED).intValue();
    }
}
