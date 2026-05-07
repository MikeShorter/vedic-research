/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.loader

/**
 * Reference data for the six Vedangas -- the auxiliary disciplines that support
 * the study and correct transmission of the Vedas.
 *
 * Source texts for the Vedangas are under `data/1_veda/5_vedang/` and include
 * Shrauta Sutras, Grihya Sutras, Parishishtas, and Pratishakhyas.
 */
object VedangasLoad {
    /** The six classical Vedanga disciplines. */
    val names = listOf(
        "Shiksha", // This auxiliary discipline has focused on the letters of the Sanskrit alphabet, accent, quantity, stress, melody and rules of euphonic combination of words during a Vedic recitation.
        "Chandas", // This auxiliary discipline has focused on the poetic meters, including those based on fixed number of syllables per verse, and those based on fixed number of morae per verse.
        "Vyakarana", // This auxiliary discipline has focused on the rules of grammar and linguistic analysis to establish the exact form of words and sentences to properly express ideas.
        "Nirukta", // This auxiliary discipline has focused on linguistic analysis to help establish the proper meaning of the words, given the context they are used in.
        "Kalpa", // This field focused on standardizing procedures for Vedic rituals, rites of passage rituals associated with major life events such as birth, wedding and death in family, as well as discussing the personal conduct and proper duties of an individual in different stages of his life.
        "Jyotisha" // This auxiliary Vedic discipline focused on time keeping.
    )
}
