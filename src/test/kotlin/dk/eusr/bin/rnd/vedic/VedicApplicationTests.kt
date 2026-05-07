/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic

import dk.eusr.bin.rnd.vedic.keys.Shruti
import dk.eusr.bin.rnd.vedic.loader.ShrutisLoad
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.mockito.Mockito
import org.springframework.boot.ApplicationArguments
import java.nio.file.Files
import java.nio.file.Path

class VedicApplicationTests {
    @TempDir
    lateinit var tempDir: Path

    private val outputFiles = listOf("lexicon.txt", "unparsed.txt", "vedas.txt", "vedas-patches.txt")

    private fun runWithMockedLoader(
        text: Map<Shruti, String> = emptyMap(),
        unparsed: Map<String, List<Map<Int, String>>> = emptyMap(),
    ) {
        Mockito
            .mockConstruction(ShrutisLoad::class.java) { mock, _ ->
                Mockito.`when`(mock.text).thenReturn(text)
                Mockito.`when`(mock.unparsed).thenReturn(unparsed)
            }.use {
                val app = VedicApplication()
                app.outputDir = tempDir
                val args = Mockito.mock(ApplicationArguments::class.java)
                app.run(args)
            }
    }

    @Test
    fun `run creates output directory if it does not exist`() {
        val subDir = tempDir.resolve("new-output")
        Mockito
            .mockConstruction(ShrutisLoad::class.java) { mock, _ ->
                Mockito.`when`(mock.text).thenReturn(emptyMap())
                Mockito.`when`(mock.unparsed).thenReturn(emptyMap())
            }.use {
                val app = VedicApplication()
                app.outputDir = subDir
                val args = Mockito.mock(ApplicationArguments::class.java)
                app.run(args)
            }

        assertTrue(Files.exists(subDir))
    }

    @Test
    fun `run succeeds when output directory already exists`() {
        runWithMockedLoader()

        assertTrue(Files.exists(tempDir))
    }

    @Test
    fun `run writes all four output files`() {
        val text = mapOf(Shruti("RV", 1, 1, 1, 1) to " word1 word2")
        val unparsed = mapOf("test.htm" to listOf(mapOf(1 to "bad line")))

        runWithMockedLoader(text, unparsed)

        outputFiles.forEach {
            assertTrue(Files.exists(tempDir.resolve(it)), "$it should exist")
        }
    }

    @Test
    fun `run writes lexicon with correct word counts`() {
        val text =
            mapOf(
                Shruti("RV", 1, 1, 1, 1) to " word1 word2",
                Shruti("RV", 1, 1, 1, 2) to " word1 word3",
            )

        runWithMockedLoader(text)

        val content = Files.readString(tempDir.resolve("lexicon.txt"))
        assertTrue(content.contains("word1 2"))
        assertTrue(content.contains("word2 1"))
        assertTrue(content.contains("word3 1"))
    }

    @Test
    fun `run writes unparsed lines to file`() {
        val unparsed = mapOf("source.htm" to listOf(mapOf(42 to "bad line content")))

        runWithMockedLoader(unparsed = unparsed)

        val content = Files.readString(tempDir.resolve("unparsed.txt"))
        assertTrue(content.contains("source.htm"))
        assertTrue(content.contains("42 : bad line content"))
    }

    @Test
    fun `run writes vedas text to file`() {
        val text = mapOf(Shruti("RV", 4, 1, 11, 2, 1) to " agním īḷe")

        runWithMockedLoader(text)

        val content = Files.readString(tempDir.resolve("vedas.txt"))
        assertTrue(content.contains("agním īḷe"))
    }

    @Test
    fun `run writes patches file from unparsed`() {
        val unparsed = mapOf("source.htm" to listOf(mapOf(1 to "RV_some.bad.line<BR>")))

        runWithMockedLoader(unparsed = unparsed)

        val content = Files.readString(tempDir.resolve("vedas-patches.txt"))
        assertTrue(content.contains("RV_some.bad.line<BR>"))
    }

    @Test
    fun `run calls load on ShrutisLoad`() {
        Mockito
            .mockConstruction(ShrutisLoad::class.java) { mock, _ ->
                Mockito.`when`(mock.text).thenReturn(emptyMap())
                Mockito.`when`(mock.unparsed).thenReturn(emptyMap())
            }.use { construction ->
                val app = VedicApplication()
                app.outputDir = tempDir
                val args = Mockito.mock(ApplicationArguments::class.java)
                app.run(args)

                val mock = construction.constructed()[0]
                Mockito.verify(mock).load()
            }
    }

    @Test
    fun `run with empty data produces empty output files`() {
        runWithMockedLoader()

        val lexicon = Files.readString(tempDir.resolve("lexicon.txt"))
        val vedas = Files.readString(tempDir.resolve("vedas.txt"))
        assertTrue(lexicon.isEmpty())
        assertTrue(vedas.isEmpty())
    }
}
