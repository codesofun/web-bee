package org.bee.webBee;

/**
 * data 2017-04-22   23:42
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public interface DataStoreBase<T>  {

    T insert(T value);

    T select();


}
