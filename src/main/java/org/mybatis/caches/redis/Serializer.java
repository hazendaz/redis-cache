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

public interface Serializer {

  /**
   * Serialize method
   *
   * @param object
   *
   * @return serialized bytes
   */
  byte[] serialize(Object object);

  /**
   * Unserialize method
   *
   * @param bytes
   *
   * @return unserialized object
   */
  Object unserialize(byte[] bytes);

  /**
   * Since kryo 5, registration required has been set 'true' for security. As such, mybatis did not expose this fact and
   * therefore was insecure in usage as never set to 'true'. To support version 5 and properly allow users to be
   * insecure if they choose so, this is being exposed to be called after creating the INSTANCE. It otherwise will
   * remain as 'true' by default per kryo 5.
   */
  public void registrationRequired(boolean required);

}
