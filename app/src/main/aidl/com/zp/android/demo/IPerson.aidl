// IPerson.aidl
package com.zp.android.demo;

// Declare any non-default types here with import statements

interface IPerson {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void  setName(String s);
    String  getName();
}
