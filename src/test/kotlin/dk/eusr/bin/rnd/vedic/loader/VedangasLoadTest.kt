/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.loader

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class VedangasLoadTest {
    @Test
    fun `names contains six vedangas`() {
        assertEquals(6, VedangasLoad.names.size)
    }

    @Test
    fun `names contains expected entries`() {
        assertTrue(VedangasLoad.names.contains("Shiksha"))
        assertTrue(VedangasLoad.names.contains("Chandas"))
        assertTrue(VedangasLoad.names.contains("Vyakarana"))
        assertTrue(VedangasLoad.names.contains("Nirukta"))
        assertTrue(VedangasLoad.names.contains("Kalpa"))
        assertTrue(VedangasLoad.names.contains("Jyotisha"))
    }
}
