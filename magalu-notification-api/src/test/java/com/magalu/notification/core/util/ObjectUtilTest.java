package com.magalu.notification.core.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ObjectUtilTest {
    @Test
    void isNotEmptyReturnsFalseForNull() {
        assertFalse(ObjectUtil.isNotEmpty(null));
    }

    @Test
    void isNotEmptyReturnsFalseForEmptyArray() {
        assertFalse(ObjectUtil.isNotEmpty(new Object[]{}));
    }

    @Test
    void isNotEmptyReturnsTrueForNonEmptyArray() {
        assertTrue(ObjectUtil.isNotEmpty(new Object[]{1}));
    }

    @Test
    void isNotEmptyReturnsTrueForNonEmptyObject() {
        assertTrue(ObjectUtil.isNotEmpty(new Object()));
    }

    @Test
    void isEmptyReturnsTrueForNull() {
        assertTrue(ObjectUtil.isEmpty(null));
    }

    @Test
    void isEmptyReturnsTrueForEmptyArray() {
        assertTrue(ObjectUtil.isEmpty(new Object[]{}));
    }

    @Test
    void isEmptyReturnsFalseForNonEmptyArray() {
        assertFalse(ObjectUtil.isEmpty(new Object[]{1}));
    }

    @Test
    void isEmptyReturnsFalseForNonEmptyObject() {
        assertFalse(ObjectUtil.isEmpty(new Object()));
    }

    @Test
    void hasSomeEmptyObjectReturnsTrueForNull() {
        assertTrue(ObjectUtil.hasSomeEmptyObject(null, null));
    }

    @Test
    void hasSomeEmptyObjectReturnsFalseForNonEmptyArray() {
        assertFalse(ObjectUtil.hasSomeEmptyObject(new Object[]{1}));
    }

    @Test
    void hasSomeEmptyObjectReturnsTrueForArrayWithNull() {
        assertTrue(ObjectUtil.hasSomeEmptyObject(new Object[]{null}));
    }

    @Test
    void hasSomeEmptyObjectReturnsTrueForArrayWithEmptyArray() {
        assertTrue(ObjectUtil.hasSomeEmptyObject(new Object[]{new Object[]{}}));
    }

    @Test
    void hasSomeEmptyObjectReturnsFalseForArrayWithNonEmptyArray() {
        assertFalse(ObjectUtil.hasSomeEmptyObject(new Object[]{new Object[]{1}}));
    }
}
