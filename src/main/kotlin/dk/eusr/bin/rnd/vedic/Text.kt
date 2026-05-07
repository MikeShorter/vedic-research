/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic

/**
 * Represents a single ITX HTML source file and the regex patterns needed to parse it.
 *
 * Each Vedic source file uses a specific verse-numbering convention. This class pairs the
 * file path with two regexes: one that captures the full structured format
 * (e.g. `RV_01.001.01.1{01}  text...`) and a looser base regex that identifies lines
 * belonging to the text but not matching the full format (used to detect unparseable lines).
 *
 * @param K the key type used to index parsed verses (e.g. [dk.eusr.bin.rnd.vedic.keys.Shruti])
 * @property source path to the ITX HTML file, relative to the project root
 * @property regex full capture regex for structured verse lines
 * @property baseRegex looser regex that matches any line belonging to this text
 */
data class Text<K>(
    val source: String,
    val regex: String,
    val baseRegex: String
) {
    /** Parsed verse entries, populated during loading. Keyed by verse location. */
    val text: MutableMap<K, String> = mutableMapOf()
}
