//package com.manuel.springsecurity.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//@Configuration
//public class Oauth2Configuration extends AuthorizationServerConfigurerAdapter {
//
//    private String clientId = "myid";
//private String clientSecret = "thisismysecret";
//private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
//        "MIIEpAIBAAKCAQEAp2sequmyFZxD4Pnul4ebMO+OKFryYYA2D08IzRdSyD9P4BXa\n" +
//        "bJBf6w/3on+tlgJnDLFYNxP3aUMty3GViY6/D4pND09y77Nx2GznJQ9w9zhcOChj\n" +
//        "XjhiuavAlZbRdIfqmhk+YoLRQOCe7c1LHYOPJdLMzysPlA/9wkdA1t4WwjF1ehfp\n" +
//        "MQKfyAXnfr2JsD/LUCtdy70jfC62+LOW3Z87wfRwbLHs0LvVTeJzxKPbK3RLQ4Qc\n" +
//        "Mp2RqeJ83AiTYlWDP/KsnR6xwYeZUnX4pwX0r3DYd9+oHQkTPYoIAjA2JWfomcvc\n" +
//        "3hH2ajbiuS3CNuyX/bC4TSpEJgqWvaAme5soDwIDAQABAoIBAAJ3FFOF72ZcD6/P\n" +
//        "XlEC4SkeTeSQOkNvRNyC2GlUr5ZdQErXZ4jzs0XdzVKqOJ8d1/Ca5+aft6PoJlN5\n" +
//        "j68f1/Zdnfbcv5+B7+o06E5mZM5vGQSGq7fmcqWzPrm1J85kdfLg7kc7D+O5qv1r\n" +
//        "aXC80C3JZdSjGK6gcacbr3u6d3FSjQCHXUycJt8fpn6T0/1nQEu1Byx73bZBVE4n\n" +
//        "wWlkWXfkTC8br8lUxCO2Yb1sPUPeFbU79ZSxnzSTOh9N7ZFb8NH2f9FAXZzk5TG2\n" +
//        "k1pz/YxTEw11gWqkBWqCN26A7bP2DQT1FHSJerPheIYVT8Fs8PH987pz/P2Z74AS\n" +
//        "2NA7RrkCgYEA3lpJ+0885jpcuEkk9oEh7iZYpIyhxbsiitxuPHLX8Fod8BevoZ6u\n" +
//        "I7F11frJ3NHZ3eRtk/EAYvY76vjsb6KTOUeYoljA/2dtsBBwO8Xf+SgcgOeJIzrh\n" +
//        "0MYTDiCESSaRX+jjBN/GilrY/de7yV/DsP5UVClL6OpIljTDZ6kSwN0CgYEAwMC8\n" +
//        "M2S0FphmKytt+pVz5WX0+hsEf/GVcPS6MYYqjFYgOvwsPJmiIOdclEpBRa7uopvt\n" +
//        "MmVfqb3hzwbo+Tovie3rvWaKCQf+TFAkwi11kVTCmb5ZeGBQGTT5xwVjtqQtBaNa\n" +
//        "iqG6P2WJ0vWmio05yD3+0dxRG2icTf7SbePAp9sCgYEA1Fjt5m9Z/ZUnD2frQO85\n" +
//        "9DHy3dLge/Gnn228aUZUK1ok1TIJrRPZwoSrwFrthVFz+5E3hN3YPKpjtpdbVZGx\n" +
//        "gDZ78i9IwaGJA2fvh2YF6/D+p6x3L4oANl2zvP4Y17NZinris7jx5xezu1jYIgyu\n" +
//        "2s1Iw8dck2eLQsfJBapWGpECgYBSGITP+t89FHHHhzrTVJ5FITTC5Yj4GWQF9SXs\n" +
//        "e/FZTkFx/f+1W9YS35etzSaZ0FTxSyouocCp4ibv70iBBghDXeNfCvJVwGLODABr\n" +
//        "98KwQIKVyt3+iD71TXvpCWGb47cNRLMGgrV51PxrrpbdQiNPJyCGL6kX3GleSt9D\n" +
//        "UJK4qwKBgQDUkeHHQSvHEKqEBN3FHT1ISK6805UCwXVBVLeHATGu03ahBfDiR7JR\n" +
//        "oIIODYESQNql1zaUvphwwDK8h+6riEUOspLO+CMT+ArSElDqiu59IR884jXxubjo\n" +
//        "Ko4oDTr+dZb2aZ8JV+++AlAa3vYVEDqlqT7FwUAM92oXbVGx6jO07g==\n" +
//        "-----END RSA PRIVATE KEY-----";
//private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
//        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp2sequmyFZxD4Pnul4eb\n" +
//        "MO+OKFryYYA2D08IzRdSyD9P4BXabJBf6w/3on+tlgJnDLFYNxP3aUMty3GViY6/\n" +
//        "D4pND09y77Nx2GznJQ9w9zhcOChjXjhiuavAlZbRdIfqmhk+YoLRQOCe7c1LHYOP\n" +
//        "JdLMzysPlA/9wkdA1t4WwjF1ehfpMQKfyAXnfr2JsD/LUCtdy70jfC62+LOW3Z87\n" +
//        "wfRwbLHs0LvVTeJzxKPbK3RLQ4QcMp2RqeJ83AiTYlWDP/KsnR6xwYeZUnX4pwX0\n" +
//        "r3DYd9+oHQkTPYoIAjA2JWfomcvc3hH2ajbiuS3CNuyX/bC4TSpEJgqWvaAme5so\n" +
//        "DwIDAQAB\n" +
//        "-----END PUBLIC KEY-----";
//
//@Autowired
//private AuthenticationManager authenticationManager;
//
//@Autowired
//private PasswordEncoder passwordEncoder;

//@Bean
//public JwtAccessTokenConverter tokenConverter(){
//    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//    converter.setSigningKey(privateKey);
//    converter.setVerifierKey(publicKey);
//    return converter;
//    }

//    @Bean
//    public JwtTokenStore tokenStore(){
//    return new JwtTokenStore(tokenConverter());
//    }

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//    // this means that for all endpoint, the endpoint must be authenticated
//        // and must have some form of token
//        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
//                .accessTokenConverter(tokenConverter());
//    }

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//    }

//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    // this part is for allowing client accessing another resources outside my platform but through my platform
//        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
//                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
//                .refreshTokenValiditySeconds(20000);
//
//    }
//}
