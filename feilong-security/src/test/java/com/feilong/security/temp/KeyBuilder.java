/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.security.temp;

import static org.apache.commons.lang3.StringUtils.defaultString;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.Validate;

import com.feilong.security.EncryptionException;
import com.feilong.tools.slf4j.Slf4jUtil;

/**
 * key 生成器.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.11.0
 * @deprecated
 */
@Deprecated
class KeyBuilder{

    /** Don't let anyone instantiate this class. */
    private KeyBuilder(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //---------------------------------------------------------------
    public static Key build(String algorithm,String keyString){
        // byte[] bytes = keyString.getBytes();
        byte[] bytes = Base64.decodeBase64(keyString);
        return new SecretKeySpec(bytes, algorithm);
        //  return extracted(algorithm, keyString);
    }

    private static Key build1(String algorithm,String keyString) throws EncryptionException{
        //this.key = new SecretKeySpec(keyString.getBytes(), algorithm);
        KeyBuilderConfig keyBuilderConfig = new KeyBuilderConfig(algorithm, keyString);
        //keyBuilderConfig.setSecureRandomAlgorithm(secureRandomAlgorithm);
        try{
            return build(keyBuilderConfig);
        }catch (NoSuchAlgorithmException e){
            //由于是固定的类型枚举,枚举里面的加密类型都经过测试过的,所以理论上来说不会再出现   NoSuchAlgorithmException
            throw new EncryptionException(
                            Slf4jUtil.format(
                                            "algorithm:[{}],keyString:[{}]",
                                            //  secureRandomAlgorithm,
                                            algorithm,
                                            keyString),
                            e);
        }
    }
    //---------------------------------------------------------------

    /**
     * 生成密钥.
     *
     * @param keyBuilderConfig
     *            the key config
     * @return 如果 <code>keyBuilderConfig</code> 是null,抛出 {@link NullPointerException}<br>
     * 
     *         如果 <code>keyBuilderConfig.getAlgorithm()</code> 是null,抛出 {@link NullPointerException}<br>
     *         如果 <code>keyBuilderConfig.getAlgorithm()</code> 是blank,抛出 {@link IllegalArgumentException}<br>
     *         如果 <code>keyBuilderConfig.getKeyString()</code> 是null,抛出 {@link NullPointerException}<br>
     *         如果 <code>keyBuilderConfig.getKeyString()</code> 是blank,抛出 {@link IllegalArgumentException}<br>
     * 
     * @throws NoSuchAlgorithmException
     *             the no such algorithm exception
     * @see <a href="http://bLOGGER.csdn.net/hbcui1984/article/details/5753083">解决Linux操作系统下AES解密失败的问题</a>
     * @see KeyGenerator
     * @see SecureRandom
     */
    public static Key build(KeyBuilderConfig keyBuilderConfig) throws NoSuchAlgorithmException{
        Validate.notNull(keyBuilderConfig, "keyConfig can't be null!");

        String algorithm = keyBuilderConfig.getAlgorithm();
        Validate.notBlank(algorithm, "keyConfig.getAlgorithm() can't be blank!");
        Validate.notBlank(keyBuilderConfig.getKeyString(), "keyConfig.getKeyString() can't be blank!");

        //---------------------------------------------------------------
        // KeyGenerator 秘密密钥生成器对象可重复使用,
        // 也就是说,在生成密钥后,可以重复使用同一个 KeyGenerator 对象来生成更多的密钥.
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

        //In Java, by default AES supports a 128 Bit key, 
        //if you plans to use 192 Bit or 256 Bit key, java complier will throw Illegal key size Exception
        int keySize = keyBuilderConfig.getKeySize();
        SecureRandom secureRandom = buildSecureRandom(keyBuilderConfig.getSecureRandomAlgorithm(), keyBuilderConfig.getKeyString());
        if (keySize <= 0){
            keyGenerator.init(secureRandom);
        }else{
            keyGenerator.init(128, secureRandom);
        }
        return keyGenerator.generateKey();
    }

    //---------------------------------------------------------------

    /**
     * Builds the secure random.
     *
     * @param keyBuilderConfig
     *            the key config
     * @return the secure random
     * @throws NoSuchAlgorithmException
     *             the no such algorithm exception
     */
    private static SecureRandom buildSecureRandom(String secureRandomAlgorithm,String keyString) throws NoSuchAlgorithmException{
        // SHA1PRNG: It is just ensuring the random number generated is as close to "truly random" as possible.
        // Easily guessable random numbers break encryption.

        // 此类提供强加密随机数生成器 (RNG). 创建一个可信任的随机数源
        String algorithm = defaultString(secureRandomAlgorithm, "SHA1PRNG");
        SecureRandom secureRandom = SecureRandom.getInstance(algorithm);

        // SecureRandom 实现完全隨操作系统本身的內部狀態,除非調用方在調用 getInstance 方法之後又調用了 setSeed 方法
        // 解决 :windows上加解密正常,linux上加密正常,解密时发生如下异常:
        // javax.crypto.BadPaddingException: Given final block not properly padded
        secureRandom.setSeed(keyString.getBytes());
        return secureRandom;
    }
}