/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.loader

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ShrutisPatchesTest {
    @Test
    fun `patches map is not empty`() {
        assertTrue(ShrutisPatches.patches.isNotEmpty())
    }

    @Test
    fun `patches contain known entries`() {
        assertTrue(
            ShrutisPatches.patches.containsKey(
                "RV_04.001.11.b apād aśīrṣā guhamāno antāyoyuvāno vṛṣabhasya nīḷe<BR>",
            ),
        )
    }

    @Test
    fun `patches remove BR tags`() {
        val key = "RV_04.001.11.b apād aśīrṣā guhamāno antāyoyuvāno vṛṣabhasya nīḷe<BR>"
        val value = ShrutisPatches.patches[key]

        assertNotNull(value)
        assertFalse(value!!.contains("<BR>"))
    }

    @Test
    fun `all patch values are non-null`() {
        ShrutisPatches.patches.forEach { (key, value) ->
            assertNotNull(value, "Value for key '$key' should not be null")
        }
    }
}
