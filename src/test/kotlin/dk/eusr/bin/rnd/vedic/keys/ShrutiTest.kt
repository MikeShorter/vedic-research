/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.keys

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ShrutiTest {
    @Test
    fun `primary constructor sets all properties`() {
        val s = Shruti("RV", 1, 2, 3, 4, 5)

        assertEquals("RV", s.veda)
        assertEquals(1, s.mandala)
        assertEquals(2, s.sukta)
        assertEquals(3, s.verse)
        assertEquals(4, s.line)
        assertEquals(5, s.varga)
    }

    @Test
    fun `varga defaults to -1`() {
        val s = Shruti("RV", 1, 2, 3, 4)

        assertEquals(-1, s.varga)
    }

    @Test
    fun `secondary constructor parses strings`() {
        val s = Shruti("RV", "01", "002", "03", "4", "05")

        assertEquals("RV", s.veda)
        assertEquals(1, s.mandala)
        assertEquals(2, s.sukta)
        assertEquals(3, s.verse)
        assertEquals(4, s.line)
        assertEquals(5, s.varga)
    }

    @Test
    fun `secondary constructor handles null values`() {
        val s = Shruti("RV", null, null, null, null, null)

        assertEquals("RV", s.veda)
        assertEquals(-1, s.mandala)
        assertEquals(-1, s.sukta)
        assertEquals(-1, s.verse)
        assertEquals(-1, s.line)
        assertEquals(-1, s.varga)
    }

    @Test
    fun `secondary constructor handles blank strings`() {
        val s = Shruti("RV", "", "  ", "", " ", "")

        assertEquals(-1, s.mandala)
        assertEquals(-1, s.sukta)
        assertEquals(-1, s.verse)
        assertEquals(-1, s.line)
        assertEquals(-1, s.varga)
    }

    @Test
    fun `secondary constructor handles all zeros`() {
        val s = Shruti("RV", "00", "000", "00", "0", "00")

        assertEquals(0, s.mandala)
        assertEquals(0, s.sukta)
        assertEquals(0, s.verse)
        assertEquals(0, s.line)
        assertEquals(0, s.varga)
    }

    @Test
    fun `secondary constructor handles leading zeros`() {
        val s = Shruti("RV", "010", "0020", "003", "04", "005")

        assertEquals(10, s.mandala)
        assertEquals(20, s.sukta)
        assertEquals(3, s.verse)
        assertEquals(4, s.line)
        assertEquals(5, s.varga)
    }

    @Test
    fun `compareTo throws NotImplementedError`() {
        val s1 = Shruti("RV", 1, 2, 3, 4)
        val s2 = Shruti("RV", 1, 2, 3, 4)

        assertThrows(NotImplementedError::class.java) {
            s1.compareTo(s2)
        }
    }

    @Test
    fun `toString formats correctly`() {
        val s = Shruti("RV", 4, 1, 11, 2, 1)

        assertEquals("RV_04.001.11.2{01}", s.toString())
    }

    @Test
    fun `toString with default varga`() {
        val s = Shruti("RV", 1, 1, 1, 1)

        // varga = -1, format will show negative
        val result = s.toString()
        assertTrue(result.startsWith("RV_01.001.01.1"))
    }
}
