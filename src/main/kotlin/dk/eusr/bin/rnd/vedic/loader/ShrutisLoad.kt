/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.loader

import dk.eusr.bin.rnd.vedic.Text
import dk.eusr.bin.rnd.vedic.keys.Shruti
import java.io.File

/**
 * Loader for the Shruti (Samhita) layer -- the primary revealed texts of the four Vedas.
 *
 * Reads ITX HTML files from `data/1_veda/1_sam/`, applies [ShrutisPatches], and parses
 * each line against the verse-marker regex. Successfully parsed lines are stored in
 * [text] keyed by [Shruti]; lines that look like verse markers but don't match the
 * full format are collected in [unparsed] for diagnostic review.
 */
class ShrutisLoad : BaseLoader() {
    /**
     * Full capture regex for the Samhita verse format.
     * Groups: (1) veda, (2) mandala, (3) sukta, (4) verse, (5) line, (7) varga, (8) text.
     * Example match: `RV_01.001.01.1{01}  agnimīḷe purohitaṃ ...`
     */
    private val shrutiFormat01 = """^(\w{2})_(\d{2})\.(\d{3})\.(\d{2})\.(\d)(\{(\d{2})})?(.*)"""

    /** Loose regex that identifies any line belonging to a Vedic text (starts with `XX_`). */
    private val shrutiBaseFormat01 = """^\w{2}_.*"""

    private val allText = mutableMapOf<Shruti, String>()
    /** Immutable snapshot of all successfully parsed verse lines, keyed by verse location. */
    val text: Map<Shruti, String>
        get() = allText.toMap()

    private val badLines = mutableMapOf<String, MutableList<Map<Int, String>>>()
    /** Lines that matched the base regex but not the full format, grouped by source file. */
    val unparsed: Map<String, List<Map<Int, String>>>
        get() = badLines.toMap()

    /**
     * Source file registry for each Veda's Samhita.
     * The Rigveda is split across ten mandala files; the other three Vedas each have one file.
     */
    val books = mapOf(
        "Rigveda" to listOf(
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_01_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_02_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_03_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_04_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_05_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_06_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_07_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_08_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_09_u.htm", shrutiFormat01, shrutiBaseFormat01),
            Text<Shruti>("data/1_veda/1_sam/1_rv/rv_10_u.htm", shrutiFormat01, shrutiBaseFormat01),
        ),
        "Yajurveda" to listOf(Text<Shruti>("data/1_veda/1_sam/maitrs_au.htm", shrutiFormat01, shrutiBaseFormat01)),
        "Samaveda" to listOf(Text<Shruti>("data/1_veda/1_sam/samavedu.htm", shrutiFormat01, shrutiBaseFormat01)),
        "Atharvaveda" to listOf(Text<Shruti>("data/1_veda/1_sam/avs___u.htm", shrutiFormat01, shrutiBaseFormat01))
    )

    /** Strips ITX HTML trailers: `<BR>` tags, verse-ending pipes (`||`), and whitespace. */
    private fun String.removeTrailer() : String = this.removeSuffix("<BR>")
        .trim()
        .removeSuffix("|")
        .removeSuffix("|")
        .trim()

    /**
     * Reads and parses all registered source files.
     *
     * For each line in each source file:
     * 1. Applies any matching entry from [ShrutisPatches] to fix known formatting errors
     * 2. Attempts to match the corrected line against [shrutiFormat01]
     * 3. On match: extracts a [Shruti] key and stores the cleaned verse text
     * 4. On mismatch: if the line matches [shrutiBaseFormat01] it is recorded in [unparsed];
     *    otherwise (HTML boilerplate, comments, etc.) the line is silently skipped
     */
    fun load() {
        books.values.forEach { veda ->
            veda.forEach { text ->
                var lineNumber = 0

                File(text.source).useLines { lines ->
                    lines.forEach { line ->
                        lineNumber++

                        val newLine = when (ShrutisPatches.patches.keys.contains(line)) {
                            true -> {
                                println("Found patched line: ${ShrutisPatches.patches[line]}")
                                ShrutisPatches.patches[line]
                            }
                            false -> line
                        }

                        if (newLine!!.matches(text.regex.toRegex())) {
                            val matchResult = text.regex.toRegex().matchEntire(newLine)
                            val key = Shruti(
                                matchResult!!.groups[1]?.value,
                                matchResult.groups[2]?.value,
                                matchResult.groups[3]?.value,
                                matchResult.groups[4]?.value,
                                matchResult.groups[5]?.value,
                                matchResult.groups[7]?.value,
                            )

                            allText[key] = matchResult.groups[8]!!.value.removeTrailer()
                        } else {
                            if (newLine.matches(text.baseRegex.toRegex())) {
                                val error = mapOf(lineNumber to line)

                                if (badLines.containsKey(text.source)) {
                                    badLines[text.source]?.add(error)
                                } else {
                                    badLines[text.source] = mutableListOf(error)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
