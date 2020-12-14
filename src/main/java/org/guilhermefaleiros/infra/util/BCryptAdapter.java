package org.guilhermefaleiros.infra.util;

import jodd.crypt.BCrypt;
import org.guilhermefaleiros.data.interfaces.Encrypter;

public class BCryptAdapter implements Encrypter {

    @Override
    public String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }
}
