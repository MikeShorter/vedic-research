/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.keys

/**
 * Verse-location key for the Brahmana layer of Vedic literature.
 *
 * The Brahmanas are prose commentaries on the Samhitas that explain the rituals
 * and their symbolism. Source texts include the Shatapatha Brahmana (Yajurveda),
 * Aitareya and Kaushitaki Brahmanas (Rigveda), and others found under `data/1_veda/2_bra/`.
 *
 * The addressing scheme mirrors the Samhita convention for now; field semantics may
 * be adjusted as more Brahmana-specific formats are added.
 *
 * @property veda two-letter code identifying the Veda (e.g. "RV", "YV", "SV", "AV")
 * @property mandala major division (Mandala, Kanda, or equivalent)
 * @property sukta sub-division (Sukta, Prapathaka, or equivalent)
 * @property verse verse number within the sukta
 * @property line line number within the verse (typically 1 or 2 for half-verses)
 * @property varga optional grouping number; -1 when not present in the source
 */
class Brahmana(
    val veda: String,
    val mandala: Int,
    val sukta: Int,
    val verse: Int,
    val line: Int,
    val varga: Int = -1
) : BaseKey<Brahmana>() {
    override fun compareTo(other: Brahmana): Int {
        TODO("Not yet implemented")
    }
}
