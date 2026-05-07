/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TextTest {
    @Test
    fun `constructor sets properties`() {
        val text = Text<String>("source.htm", "regex", "baseRegex")

        assertEquals("source.htm", text.source)
        assertEquals("regex", text.regex)
        assertEquals("baseRegex", text.baseRegex)
    }

    @Test
    fun `text map is initially empty`() {
        val text = Text<String>("s", "r", "b")

        assertTrue(text.text.isEmpty())
    }

    @Test
    fun `text map is mutable`() {
        val text = Text<String>("s", "r", "b")

        text.text["key"] = "value"

        assertEquals(1, text.text.size)
        assertEquals("value", text.text["key"])
    }

    @Test
    fun `data class equals works`() {
        val t1 = Text<String>("s", "r", "b")
        val t2 = Text<String>("s", "r", "b")

        assertEquals(t1, t2)
    }

    @Test
    fun `data class hashCode works`() {
        val t1 = Text<String>("s", "r", "b")
        val t2 = Text<String>("s", "r", "b")

        assertEquals(t1.hashCode(), t2.hashCode())
    }

    @Test
    fun `data class toString works`() {
        val text = Text<String>("s", "r", "b")

        assertTrue(text.toString().contains("s"))
        assertTrue(text.toString().contains("r"))
        assertTrue(text.toString().contains("b"))
    }

    @Test
    fun `data class copy works`() {
        val t1 = Text<String>("s", "r", "b")
        val t2 = t1.copy(source = "new")

        assertEquals("new", t2.source)
        assertEquals("r", t2.regex)
    }

    @Test
    fun `data class component functions work`() {
        val text = Text<String>("s", "r", "b")
        val (source, regex, baseRegex) = text

        assertEquals("s", source)
        assertEquals("r", regex)
        assertEquals("b", baseRegex)
    }
}
