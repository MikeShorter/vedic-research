/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path

class UtilsTest {
    @Test
    fun `generateLexicon counts words correctly`() {
        val text =
            mapOf(
                1 to "a word word another",
                2 to "b word third",
            )

        val lexicon = Utils.generateLexicon(text)

        // First word of each line (after split) at index 0 is skipped
        // Line 1: skips "a", counts "word"=2, "another"=1
        // Line 2: skips "b", counts "word"=1, "third"=1
        assertEquals(3, lexicon["word"])
        assertEquals(1, lexicon["another"])
        assertEquals(1, lexicon["third"])
        assertNull(lexicon["a"])
        assertNull(lexicon["b"])
    }

    @Test
    fun `generateLexicon with empty map`() {
        val lexicon = Utils.generateLexicon(emptyMap<Int, String>())

        assertTrue(lexicon.isEmpty())
    }

    @Test
    fun `generateLexicon with single-word lines`() {
        val text = mapOf(1 to "onlyword")

        val lexicon = Utils.generateLexicon(text)

        // Single word at index 0 is skipped
        assertTrue(lexicon.isEmpty())
    }

    @Test
    fun `totalWords sums all counts`() {
        val lexicon = mapOf("a" to 3, "b" to 5, "c" to 2)

        assertEquals(10, Utils.totalWords(lexicon))
    }

    @Test
    fun `totalWords with empty map`() {
        assertEquals(0, Utils.totalWords(emptyMap()))
    }

    @Test
    fun `writeLexicon writes sorted entries`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("lexicon.txt")
        val lexicon = mapOf("banana" to 3, "apple" to 1, "cherry" to 2)

        Utils.writeLexicon(file, lexicon)

        val lines = Files.readAllLines(file)
        assertEquals(3, lines.size)
        assertEquals("apple 1", lines[0])
        assertEquals("banana 3", lines[1])
        assertEquals("cherry 2", lines[2])
    }

    @Test
    fun `writeLexicon with empty map`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("lexicon.txt")

        Utils.writeLexicon(file, emptyMap())

        val lines = Files.readAllLines(file)
        assertTrue(lines.isEmpty())
    }

    @Test
    fun `writeUnparsed writes sections with headers`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("unparsed.txt")
        val unparsed =
            mapOf(
                "source1.htm" to listOf(mapOf(10 to "bad line 1")),
                "source2.htm" to listOf(mapOf(20 to "bad line 2"), mapOf(30 to "bad line 3")),
            )

        Utils.writeUnparsed(file, unparsed)

        val content = Files.readString(file)
        assertTrue(content.contains("---- source1.htm ----"))
        assertTrue(content.contains("10 : bad line 1"))
        assertTrue(content.contains("---- source2.htm ----"))
        assertTrue(content.contains("20 : bad line 2"))
        assertTrue(content.contains("30 : bad line 3"))
    }

    @Test
    fun `writeUnparsed with empty map`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("unparsed.txt")

        Utils.writeUnparsed(file, emptyMap())

        val content = Files.readString(file)
        assertTrue(content.isEmpty())
    }

    @Test
    fun `writePatches generates patch format`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("patches.txt")
        val unparsed =
            mapOf(
                "source.htm" to listOf(mapOf(10 to "RV_01.001.01.a some text<BR>")),
            )

        Utils.writePatches(file, unparsed)

        val content = Files.readString(file)
        assertTrue(content.contains("\"RV_01.001.01.a some text<BR>\" to"))
        assertTrue(content.contains("\"RV_01.001.01.a some text\","))
    }

    @Test
    fun `writePatches removes trailing pipes and whitespace`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("patches.txt")
        val unparsed =
            mapOf(
                "source.htm" to listOf(mapOf(10 to "some text || <BR>")),
            )

        Utils.writePatches(file, unparsed)

        val content = Files.readString(file)
        assertTrue(content.contains("\"some text\","))
    }

    @Test
    fun `writePatches with empty map`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("patches.txt")

        Utils.writePatches(file, emptyMap())

        val content = Files.readString(file)
        assertTrue(content.isEmpty())
    }

    @Test
    fun `writeText writes key-value pairs`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("text.txt")
        val text = mapOf("key1" to "value1", "key2" to "value2")

        Utils.writeText(file, text)

        val content = Files.readString(file)
        assertTrue(content.contains("key1 value1"))
        assertTrue(content.contains("key2 value2"))
    }

    @Test
    fun `writeText with empty map`(
        @TempDir tempDir: Path,
    ) {
        val file = tempDir.resolve("text.txt")

        Utils.writeText(file, emptyMap<String, String>())

        val content = Files.readString(file)
        assertTrue(content.isEmpty())
    }
}
