/*
 *    Copyright 2015-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.caches.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SerializerUnregisteredTest {

  Serializer kryoSerializer;

  @BeforeEach
  void setup() {
    kryoSerializer = KryoSerializer.INSTANCE;
    kryoSerializer.registrationRequired(false);
  }

  @Test
  void testKryoUnserializeWithoutRegistry() throws IOException {
    SimpleBeanStudentInfo rawSimpleBean = new SimpleBeanStudentInfo();

    byte[] serialBytes = kryoSerializer.serialize(rawSimpleBean);

    Kryo kryoWithoutRegisty = new Kryo();
    kryoWithoutRegisty.setRegistrationRequired(false);
    Input input = new Input(serialBytes);
    SimpleBeanStudentInfo unserializeSimpleBean = (SimpleBeanStudentInfo) kryoWithoutRegisty.readClassAndObject(input);
    assertEquals(rawSimpleBean, unserializeSimpleBean);
  }

}
