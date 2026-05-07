/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.keys

/**
 * Verse-location key for the Shruti (Samhita) layer of Vedic literature.
 *
 * "Shruti" ("that which is heard") refers to the primary revealed texts -- the four
 * Samhitas: Rigveda, Yajurveda (here the Maitrayani Samhita), Samaveda, and
 * Atharvaveda. The ITX source files use a Mandala/Sukta/Verse numbering scheme:
 *
 *     RV_01.001.01.1{01}  agnimīḷe purohitaṃ ...
 *     ^^  ^^  ^^^ ^^ ^ ^^
 *     |   |   |   |  | └─ varga (optional sub-grouping)
 *     |   |   |   |  └─── line within the verse (half-verse marker)
 *     |   |   |   └────── verse number
 *     |   |   └────────── sukta (hymn number)
 *     |   └────────────── mandala (book number)
 *     └────────────────── veda code (RV, MS, SV, AV, etc.)
 *
 * @property veda two-letter code identifying the Veda
 * @property mandala book/mandala number
 * @property sukta hymn/sukta number
 * @property verse verse (mantra) number within the sukta
 * @property line half-verse line (typically 1 or 2)
 * @property varga optional sub-grouping number; -1 when absent
 */
class Shruti (
    val veda: String,
    val mandala: Int,
    val sukta: Int,
    val verse: Int,
    val line: Int,
    val varga: Int = -1
) : BaseKey<Shruti>() {
    /**
     * Secondary constructor that parses string values captured by regex groups.
     * Null or blank strings are treated as -1 (absent); leading zeros are stripped.
     */
    constructor(veda: String?, mandala: String?, sukta: String?, verse: String?, line: String?, varga: String?) :
        this(veda!!, mandala.toInteger(), sukta.toInteger(), verse.toInteger(), line.toInteger(), varga.toInteger())

    override fun compareTo(other: Shruti): Int {
        TODO("Not yet implemented")
    }

    /** Formats the key in the canonical ITX notation, e.g. `RV_04.001.11.2{01}`. */
    override fun toString(): String {
        return "%2s_%02d.%03d.%02d.%1d{%02d}".format(veda, mandala, sukta, verse, line, varga)
    }

    companion object {
        /**
         * Parses a nullable numeric string from a regex capture group into an Int.
         * Returns -1 for null/blank, 0 for all-zeros, otherwise the numeric value
         * with leading zeros removed.
         */
        private fun String?.toInteger(): Int {
            return when ((this == null) || this.isBlank()) {
                true -> -1
                false -> {
                    val numString = this.trimStart('0')

                    when (numString.isBlank()) {
                        true -> 0
                        false -> this.trimStart('0').toInt()
                    }
                }
            }
        }
    }
}
