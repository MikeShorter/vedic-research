/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

/**
 * Utility functions for building and writing lexicons and diagnostic files
 * from parsed Vedic text.
 */
object Utils {
    /**
     * Builds a word-frequency map from parsed verse text.
     *
     * Each map value is a line of verse text whose first whitespace-delimited token
     * is a structural marker (half-verse letter, punctuation, etc.) and is therefore
     * skipped. All subsequent tokens are counted as words.
     *
     * @param T the verse-key type
     * @return map of word to occurrence count across all verses
     */
    fun <T> generateLexicon(text: Map<T, String>): Map<String, Int> {
        val allWords = mutableMapOf<String, Int>()

        text.values.forEach { line ->
            line.split("""\s+""".toRegex()).forEachIndexed { index, word ->
                if (index != 0) {
                    if (allWords.containsKey(word)) {
                        allWords[word] = allWords[word]!! + 1
                    } else {
                        allWords[word] = 1
                    }
                }
            }
        }

        return allWords.toMap()
    }

    /** Returns the total number of word occurrences across the entire lexicon. */
    fun totalWords(lexicon: Map<String, Int>): Int {
        var total = 0

        lexicon.forEach { (_, u) -> total += u }

        return total
    }

    /** Writes the lexicon as a sorted, space-delimited `word count` text file. */
    fun writeLexicon(
        file: Path,
        lexicon: Map<String, Int>,
    ) {
        Files.newBufferedWriter(file).use { writer ->
            lexicon.toSortedMap().forEach { (t, u) ->
                writer.write("$t $u\n")
            }
        }
    }

    /**
     * Writes unparseable lines grouped by source file, for manual inspection.
     * Each section is headed with the source file path; entries show `lineNumber : rawLine`.
     */
    fun writeUnparsed(
        file: Path,
        unparsed: Map<String, List<Map<Int, String>>>,
    ) {
        Files.newBufferedWriter(file).use { stream ->
            unparsed.keys.forEach { key ->
                stream.write("\n---- $key ----\n")
                unparsed[key]?.forEach { it.forEach { (t, u) -> stream.write("$t : $u\n") } }
            }
        }
    }

    /**
     * Strips ITX HTML line trailers: `<BR>` tags, trailing pipe characters (`|` or `||`),
     * and surrounding whitespace. Used to produce clean patch suggestions.
     */
    private fun String.removeTrailer(): String =
        this
            .removeSuffix("<BR>")
            .trim()
            .removeSuffix("|")
            .removeSuffix("|")
            .trim()

    /**
     * Generates suggested [ShrutisPatches][dk.eusr.bin.rnd.vedic.loader.ShrutisPatches]-format
     * entries from unparseable lines. Each entry is written as a Kotlin map-literal pair:
     *
     *     "original line" to
     *     "cleaned line",
     *
     * These can be reviewed and copied into [ShrutisPatches] to fix known formatting issues.
     */
    fun writePatches(
        file: Path,
        unparsed: Map<String, List<Map<Int, String>>>,
    ) {
        Files.newBufferedWriter(file).use { writer ->
            unparsed.keys.forEach { key ->
                unparsed[key]?.forEach { it.forEach { (_, u) -> writer.write("\"$u\" to\n\"${u.removeTrailer()}\",\n") } }
            }
        }
    }

    /** Writes the full parsed text as `key text` lines, preserving insertion order. */
    fun <T> writeText(
        file: Path,
        text: Map<T, String>,
    ) {
        Files.newBufferedWriter(file).use { writer ->
            text.forEach { (t, u) -> writer.write("$t $u\n") }
        }
    }
}
