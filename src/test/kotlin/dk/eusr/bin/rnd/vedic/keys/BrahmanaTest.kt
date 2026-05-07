/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.keys

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class BrahmanaTest {
    @Test
    fun `constructor sets all properties`() {
        val b = Brahmana("RV", 1, 2, 3, 4, 5)

        assertEquals("RV", b.veda)
        assertEquals(1, b.mandala)
        assertEquals(2, b.sukta)
        assertEquals(3, b.verse)
        assertEquals(4, b.line)
        assertEquals(5, b.varga)
    }

    @Test
    fun `varga defaults to -1`() {
        val b = Brahmana("RV", 1, 2, 3, 4)

        assertEquals(-1, b.varga)
    }

    @Test
    fun `compareTo throws NotImplementedError`() {
        val b1 = Brahmana("RV", 1, 2, 3, 4)
        val b2 = Brahmana("RV", 1, 2, 3, 4)

        assertThrows(NotImplementedError::class.java) {
            b1.compareTo(b2)
        }
    }
}
