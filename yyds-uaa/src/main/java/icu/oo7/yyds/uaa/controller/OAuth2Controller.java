package icu.oo7.yyds.uaa.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import icu.oo7.yyds.common.restful.ResponseData;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/publicKey")
    public ResponseData<JSONObject> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return ResponseData.success(new JWKSet(key).toJSONObject());
    }

}
