package com.example.app;

import java.io.InputStream;

/**
 * @author Yoshimasa Tanabe
 */
public interface ArgumentResolver {

  Argument resolve(InputStream stream);

}
