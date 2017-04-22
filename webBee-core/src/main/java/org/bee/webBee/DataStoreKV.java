package org.bee.webBee;

/**
 * data 2017-04-22   23:42
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public interface DataStoreKV<T> extends DataStoreBase {

    T insert(String key,T value);

}
