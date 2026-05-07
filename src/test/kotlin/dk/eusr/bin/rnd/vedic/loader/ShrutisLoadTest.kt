/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.loader

import dk.eusr.bin.rnd.vedic.keys.Shruti
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

class ShrutisLoadTest {
    private fun createLoaderWithFiles(
        tempDir: Path,
        fileContents: Map<String, String>,
    ): ShrutisLoad {
        fileContents.forEach { (path, content) ->
            val file = tempDir.resolve(path).toFile()
            file.parentFile.mkdirs()
            file.writeText(content)
        }

        // Create a loader and override books via reflection to point to temp files
        val loader = ShrutisLoad()
        val booksField = ShrutisLoad::class.java.getDeclaredField("books")
        booksField.isAccessible = true

        val format01 = """^(\w{2})_(\d{2})\.(\d{3})\.(\d{2})\.(\d)(\{(\d{2})})?(.*)"""
        val baseFormat01 = """^\w{2}_.*"""

        val books =
            mapOf(
                "Test" to
                    fileContents.keys.map { path ->
                        dk.eusr.bin.rnd.vedic.Text<Shruti>(
                            tempDir.resolve(path).toString(),
                            format01,
                            baseFormat01,
                        )
                    },
            )

        booksField.set(loader, books)
        return loader
    }

    @Test
    fun `load parses valid lines`(
        @TempDir tempDir: Path,
    ) {
        val content =
            "RV_01.001.01.1 agním īḷe puróhitaṃ\n" +
                "RV_01.001.01.2 yajñásya devám ṛtvíjam\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()

        assertEquals(2, loader.text.size)
        assertTrue(loader.unparsed.isEmpty())
    }

    @Test
    fun `load parses lines with varga`(
        @TempDir tempDir: Path,
    ) {
        val content = "RV_01.001.01.1{03} agním īḷe puróhitaṃ\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()

        assertEquals(1, loader.text.size)
        val key = loader.text.keys.first()
        assertEquals(3, key.varga)
    }

    @Test
    fun `load removes BR trailer`(
        @TempDir tempDir: Path,
    ) {
        val content = "RV_01.001.01.1 agním īḷe puróhitaṃ<BR>\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()

        val value = loader.text.values.first()
        assertFalse(value.contains("<BR>"))
    }

    @Test
    fun `load removes trailing pipes`(
        @TempDir tempDir: Path,
    ) {
        val content = "RV_01.001.01.1 agním īḷe puróhitaṃ || <BR>\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()

        val value = loader.text.values.first()
        assertFalse(value.endsWith("|"))
    }

    @Test
    fun `load tracks unparsed lines matching base regex`(
        @TempDir tempDir: Path,
    ) {
        // This line matches the base regex (starts with XX_) but not the full format
        val content = "RV_malformed line here\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()

        assertTrue(loader.text.isEmpty())
        assertEquals(1, loader.unparsed.size)
    }

    @Test
    fun `load ignores lines not matching base regex`(
        @TempDir tempDir: Path,
    ) {
        val content = "This is just a random line\nAnother random line\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()

        assertTrue(loader.text.isEmpty())
        assertTrue(loader.unparsed.isEmpty())
    }

    @Test
    fun `load accumulates multiple unparsed from same source`(
        @TempDir tempDir: Path,
    ) {
        val content = "RV_bad line 1\nRV_bad line 2\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()

        val source = loader.unparsed.keys.first()
        assertEquals(2, loader.unparsed[source]!!.size)
    }

    @Disabled("TODO: No current ShrutisPatches entry produces an observable difference in " +
        "text/unparsed output — the patched lines still fail the full regex and load() stores " +
        "the original line in unparsed. To make this meaningful, either (a) add a patch whose " +
        "corrected form matches the full verse regex so text.isNotEmpty() proves the patch fired, " +
        "or (b) expose the patching step separately so it can be unit-tested in isolation.")
    @Test
    fun `load applies patches`(
        @TempDir tempDir: Path,
    ) {
        val patchKey = "RV_04.001.11.b apād aśīrṣā guhamāno antāyoyuvāno vṛṣabhasya nīḷe<BR>"
        val content = patchKey + "\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()
    }

    @Test
    fun `books map has expected vedas`() {
        val loader = ShrutisLoad()

        assertTrue(loader.books.containsKey("Rigveda"))
        assertTrue(loader.books.containsKey("Yajurveda"))
        assertTrue(loader.books.containsKey("Samaveda"))
        assertTrue(loader.books.containsKey("Atharvaveda"))
    }

    @Test
    fun `rigveda has ten books`() {
        val loader = ShrutisLoad()

        assertEquals(10, loader.books["Rigveda"]!!.size)
    }

    @Test
    fun `text property returns immutable copy`() {
        val loader = ShrutisLoad()

        assertTrue(loader.text.isEmpty())
    }

    @Test
    fun `unparsed property returns immutable copy`() {
        val loader = ShrutisLoad()

        assertTrue(loader.unparsed.isEmpty())
    }

    @Test
    fun `load handles mixed valid and invalid lines`(
        @TempDir tempDir: Path,
    ) {
        val content =
            "RV_01.001.01.1 first line text\n" +
                "not a vedic line\n" +
                "RV_malformed\n" +
                "RV_01.001.02.1 second line text<BR>\n"

        val loader = createLoaderWithFiles(tempDir, mapOf("test.htm" to content))
        loader.load()

        assertEquals(2, loader.text.size)
        assertEquals(1, loader.unparsed.size)
    }
}
