package com.peeyush.helper;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * This class is helper class.
 * It is a very very simple dumb Numeric Long Number Generator, Just for demo purpose.
 * @link https://gist.github.com/umbertogriffo/956f3dacc7fbeee0b65a264e8b003044
 */
public class SimpleInMemoryNumericIdGenerator {

  public static Long generateUniqueId() {
    long val = -1;
    do {
      final UUID uid = UUID.randomUUID();
      final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
      buffer.putLong(uid.getLeastSignificantBits());
      buffer.putLong(uid.getMostSignificantBits());
      final BigInteger bi = new BigInteger(buffer.array());
      val = bi.longValue();
    }
    // We also make sure that the ID is in positive space, if its not we simply repeat the process
    while (val < 0);
    return val;
  }
}
