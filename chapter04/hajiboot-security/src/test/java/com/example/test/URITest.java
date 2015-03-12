package com.example.test;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author Yoshimasa Tanabe
 */
public class URITest {

  @Test
  public void test() throws Exception {
    URI uri = new URI("postgres://asdf:foobar@ec2-21-114-88-77.compute-1.amazonaws.com:5432/sfd36tr463cuu8");

    Assert.assertThat(uri.getHost(), is("ec2-21-114-88-77.compute-1.amazonaws.com"));
    Assert.assertThat(uri.getPort(), is(5432));
    Assert.assertThat(uri.getPath(), is("/sfd36tr463cuu8"));

    Assert.assertThat("jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath(),
      is("jdbc:postgresql://ec2-21-114-88-77.compute-1.amazonaws.com:5432/sfd36tr463cuu8"));

    Assert.assertThat(uri.getUserInfo(), is("asdf:foobar"));
  }
}
